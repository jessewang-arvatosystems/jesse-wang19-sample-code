package com.fdm.databases;

import java.util.TreeMap;

import com.fdm.accounts.Account;
import com.fdm.accounts.SetupAnAccount;

public class ByteIO_DAO implements _AccountDatabase {

	private TreeMap<Integer, Account> accountInfo;
	private SetupAnAccount setupAccount;
	private ByteIO_DTO dataTransferObject = new ByteIO_DTO();

	public ByteIO_DAO() {
		accountInfo = new TreeMap<Integer, Account>();
		setupAccount = SetupAnAccount.INSTANCE;
		accountInfo = dataTransferObject.readByteFile();
	}

	public void createAccount(Account account) {
		accountInfo.put(setupAccount.getAccountNumber(), account);
		dataTransferObject.writeByteFile(accountInfo);
	}

	public Account retrieveAccount(int accountNumber) {
		return accountInfo.get(accountNumber);
	}

	public void updateAccount(int accountNumber, double newBalance) {

		accountInfo = dataTransferObject.readByteFile();
		accountInfo.get(accountNumber).setBalance(newBalance);
		dataTransferObject.writeByteFile(accountInfo);
	}

	public boolean accountExists(int accountNumber) {

		accountInfo = dataTransferObject.readByteFile();
		return accountInfo.containsKey(accountNumber);
	}

	public void deleteAccount(int accountNumber) {

		accountInfo = dataTransferObject.readByteFile();
		accountInfo.remove(accountNumber);
		dataTransferObject.writeByteFile(accountInfo);
	}

	public int getHighestAccountNumber() {

		if (!accountInfo.isEmpty())
			return accountInfo.lastKey();
		else
			return 1;
	}

}