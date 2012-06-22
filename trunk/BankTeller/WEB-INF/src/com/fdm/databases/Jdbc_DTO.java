package com.fdm.databases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;

import com.fdm.accounts.Account;
import com.fdm.accounts.AccountCreatorFactory;
import com.fdm.accounts.AccountType;
import com.fdm.exceptions.BankTellerException;
import com.fdm.exceptions.InvalidQueryStringsFileException;
import com.fdm.helper.ConnectionHelper;

public class Jdbc_DTO {

	private AccountCreatorFactory accountFactory = AccountCreatorFactory.INSTANCE;

	private String userName, password, driverType, serverName, databaseName;
	private final static String JDBC_CONNECTION_INFO_FILE = "resources/BankTellerJDBCConnectionInfoFile.txt";
	private int port;
	private Properties jdbcConnectionProperties = new Properties();

	public Jdbc_DTO() {
		obtainConnectionInfo();
	}

	public void obtainConnectionInfo() {

		InputStream in = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream(JDBC_CONNECTION_INFO_FILE);
			jdbcConnectionProperties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.closeConnection(in);
		}
		userName = jdbcConnectionProperties.getProperty("USER_NAME");
		password = jdbcConnectionProperties.getProperty("PASSWORD");
		driverType = jdbcConnectionProperties.getProperty("DRIVER_TYPE");
		serverName = jdbcConnectionProperties.getProperty("SERVER_NAME");
		port = Integer.parseInt(jdbcConnectionProperties.getProperty("PORT"));
		databaseName = jdbcConnectionProperties.getProperty("DATABASE_NAME");
	}

	private Connection connectToDatabase() throws SQLException,
			ClassNotFoundException {

		OracleDataSource oracleDataSource = new OracleDataSource();

		oracleDataSource.setDriverType(driverType);
		oracleDataSource.setServerName(serverName);
		oracleDataSource.setPortNumber(port);
		oracleDataSource.setDatabaseName(databaseName);
		oracleDataSource.setUser(userName);
		oracleDataSource.setPassword(password);

		return oracleDataSource.getConnection();
	}

	private PreparedStatement prepareSQLUpdate(String queryStatement,
			Connection connection) throws SQLException, BankTellerException {
		PreparedStatement preparedStatment;

		verifyQueryString(queryStatement);
		preparedStatment = connection.prepareStatement(queryStatement);
		return preparedStatment;
	}

	private void finishSQLUpdate(PreparedStatement statement,
			Connection connection) throws SQLException {
		statement.executeUpdate();
		connection.commit();
		connection.close();
	}

	private void finishSQLRetrieval(ResultSet resultSet, Connection connection)
			throws SQLException {
		resultSet.close();
		connection.close();
	}

	public void createAccountInDatabase(String queryStatement, Account account)
			throws BankTellerException {
		Connection connection = null;
		PreparedStatement createAccount = null;
		try {
			connection = connectToDatabase();
			connection.setAutoCommit(false);
			createAccount = prepareSQLUpdate(queryStatement, connection);
			setupCreatingAnAccount(createAccount, account);
			finishSQLUpdate(createAccount, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.closeConnection(createAccount);
		}
	}

	public Account retrieveAccountFromDatabase(String queryStatement,
			int accountNumber) throws BankTellerException {

		ResultSet accountInfo;
		PreparedStatement getAccount = null;
		Connection connection = null;
		Account account = null;
		try {
			connection = connectToDatabase();
			getAccount = prepareSQLUpdate(queryStatement, connection);
			getAccount.setInt(1, accountNumber);
			getAccount.executeQuery();
			accountInfo = getAccount.getResultSet();
			if (accountInfo.next())
				account = resultSetToAccount(accountInfo);
			finishSQLRetrieval(accountInfo, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.closeConnection(getAccount);
		}
		return account;
	}

	public void updateAccountInDatabase(String queryStatement,
			int accountNumber, double balance) throws BankTellerException {

		PreparedStatement updateAccount = null;
		Connection connection = null;
		try {
			connection = connectToDatabase();
			connection.setAutoCommit(false);
			updateAccount = prepareSQLUpdate(queryStatement, connection);
			updateAccount.setInt(2, accountNumber);
			updateAccount.setDouble(1, balance);
			finishSQLUpdate(updateAccount, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.closeConnection(updateAccount);
		}
	}

	public void deleteAccountInDatabase(String queryStatement, int accountNumber)
			throws BankTellerException {

		PreparedStatement deleteAccount = null;
		Connection connection = null;
		try {
			connection = connectToDatabase();
			connection.setAutoCommit(false);
			deleteAccount = prepareSQLUpdate(queryStatement, connection);
			deleteAccount.setInt(1, accountNumber);
			finishSQLUpdate(deleteAccount, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.closeConnection(deleteAccount);
		}
	}

	public int retrieveHighestAccountNumberFromDatabase(String queryStatement)
			throws BankTellerException {

		int highestAccountNo = 0;
		ResultSet accountInfo;
		PreparedStatement getHighestAccountNumber = null;
		Connection connection = null;
		try {
			connection = connectToDatabase();
			getHighestAccountNumber = prepareSQLUpdate(queryStatement,
					connection);
			getHighestAccountNumber.executeQuery();
			accountInfo = getHighestAccountNumber.getResultSet();
			if (accountInfo.next()) {
				highestAccountNo = accountInfo.getInt("ACCOUNT_NUMBER");
			}
			finishSQLRetrieval(accountInfo, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.closeConnection(getHighestAccountNumber);
		}

		return highestAccountNo;
	}

	private Account resultSetToAccount(ResultSet accountInfo)
			throws SQLException {

		String accountType = accountInfo.getString("ACCOUNT_TYPE");
		Account account = accountFactory.createAccount(AccountType.valueOf(
				accountType).getEnumValue());
		account.setAccountNumber(accountInfo.getInt("ACCOUNT_NUMBER"));
		account.setAccountType(accountType);
		account.setName(accountInfo.getString("NAME"));
		account.setBalance(accountInfo.getDouble("BALANCE"));
		account.setOverdraft(accountInfo.getDouble("OVERDRAFT_LIMIT"));

		return account;
	}

	private void setupCreatingAnAccount(PreparedStatement createAccount,
			Account account) throws SQLException {

		createAccount.setInt(1, account.getAccountNumber());
		createAccount.setString(2, account.getAccountType());
		createAccount.setString(3, account.getName());
		createAccount.setDouble(4, account.getBalance());
		createAccount.setDouble(5, account.getOverdraft());
	}

	private void verifyQueryString(String queryString)
			throws BankTellerException {
		if (queryString == null)
			throw new InvalidQueryStringsFileException();
	}

}
