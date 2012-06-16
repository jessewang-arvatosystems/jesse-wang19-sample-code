package com.fdm.bank;

import java.util.ArrayList;

import com.fdm.accounts.Account;
import com.fdm.accounts.AccountType;
import com.fdm.accounts.SetupAnAccount;
import com.fdm.databases.SelectDatabase;
import com.fdm.databases._AccountDatabase;
import com.fdm.exceptions.BankTellerException;
import com.fdm.exceptions.CannotCloseAccountException;
import com.fdm.exceptions.InvalidAccountNumberException;
import com.fdm.exceptions.InvalidDatabaseTypeException;
import com.fdm.exceptions.NegativeNumberException;

public enum BankGateway {
	
	INSTANCE;
	
	private SelectDatabase selectDatabase = SelectDatabase.INSTANCE;
	private SetupAnAccount setupAnAccount = SetupAnAccount.INSTANCE;
	private _AccountDatabase accountDatabase = selectDatabase.selectDatabaseType();
	
	public BankAccountOutputVO createCurrentAccount(String name, double initialDeposit, double overdraftLimit) throws BankTellerException {
		
		verifyDatabase();
		accountDatabase.createAccount(setupAnAccount.setupAccount(AccountType.CURRENT, name, initialDeposit, overdraftLimit));
		return selectMostRecentlyCreatedAccount();
	}

	public BankAccountOutputVO createSavingsAccount(String name, double initialDeposit) throws BankTellerException {
		
		verifyDatabase();
		accountDatabase.createAccount(setupAnAccount.setupAccount(AccountType.SAVINGS, name, initialDeposit, 0d));
		return selectMostRecentlyCreatedAccount();
	}
	
	public BankAccountOutputVO closeAccount(int accountNumber) throws BankTellerException {
		
		verifyDatabase();
		verifyAccountNumber(accountDatabase, accountNumber);
		
		if (accountDatabase.retrieveAccount(accountNumber).getBalance() == 0d)
			accountDatabase.deleteAccount(accountNumber);
		else
			throw new CannotCloseAccountException();
	
		BankAccountOutputVO closeAccountSummary = 
			new BankAccountOutputVO(Integer.toString(accountNumber), null, null, null, null);
		
		return closeAccountSummary;
	}

	public BankAccountOutputVO makeDeposit (double depositAmount, int accountNumber) throws BankTellerException {
		
		verifyDatabase();
		verifyAccountNumber(accountDatabase, accountNumber);
		
		Account account = accountDatabase.retrieveAccount(accountNumber);
		account.makeDeposit(depositAmount);
		accountDatabase.updateAccount(accountNumber, account.getBalance());
		return viewAccountSummary(accountNumber);
	}

	public BankAccountOutputVO makeWithdrawal(double withdrawalAmount, int accountNumber) throws BankTellerException {
		
		verifyDatabase();
		verifyAccountNumber(accountDatabase, accountNumber);
		
		Account account = accountDatabase.retrieveAccount(accountNumber);
		account.makeWithdrawal(withdrawalAmount);
		accountDatabase.updateAccount(accountNumber, account.getBalance());
		return viewAccountSummary(accountNumber);
	}
	
	public BankAccountOutputVO viewAccountSummary(int accountNumber) throws BankTellerException {
		
		verifyDatabase();
		verifyAccountNumber(accountDatabase, accountNumber);
		
		return prepareAccountSummary(accountDatabase.retrieveAccount(accountNumber));
	}
	
	public ArrayList<BankAccountOutputVO> listAllAccounts() throws BankTellerException {
		
		return prepareListOfAccounts();
	}
	
	private BankAccountOutputVO prepareAccountSummary(Account anAccount) throws BankTellerException {
		
		BankAccountOutputVO accountSummary = new 
			BankAccountOutputVO(Integer.toString(anAccount.getAccountNumber()), 
								anAccount.getAccountType(), 
								anAccount.getName(), 
								getOverdraftString(anAccount.getAccountNumber()),
								Double.toString(anAccount.getBalance()));
		return accountSummary;
	}
	
	private String getOverdraftString(int accountNumber) throws BankTellerException {
		
		verifyDatabase();
		return Double.toString((accountDatabase.retrieveAccount(accountNumber)).getOverdraft());
	}
	
	private ArrayList<BankAccountOutputVO> prepareListOfAccounts() throws BankTellerException {
		
		verifyDatabase();
		ArrayList<BankAccountOutputVO> listOfAccounts = new ArrayList<BankAccountOutputVO>();
		// getAccountNumber returns the highest account number in the database
		for (int anAccountNo = 1; anAccountNo <= accountDatabase.getHighestAccountNumber(); anAccountNo++) {
			if (accountDatabase.accountExists(anAccountNo))
				listOfAccounts.add(prepareAccountSummary(accountDatabase.retrieveAccount(anAccountNo)));
		}
		
		return listOfAccounts;
	}
	
	private BankAccountOutputVO selectMostRecentlyCreatedAccount() throws InvalidAccountNumberException, NegativeNumberException, BankTellerException {

		verifyDatabase();
		BankAccountOutputVO accountVO = null;
		accountVO = prepareAccountSummary(accountDatabase.retrieveAccount(accountDatabase.getHighestAccountNumber()));
	
		return accountVO;
	}
	
	private void verifyDatabase() throws BankTellerException {
		if (accountDatabase == null)
			throw new InvalidDatabaseTypeException();
	}
	
	private void verifyAccountNumber(_AccountDatabase database, int accountNumber) throws BankTellerException {
		if (!database.accountExists(accountNumber))
			throw new InvalidAccountNumberException();
	}
	
}