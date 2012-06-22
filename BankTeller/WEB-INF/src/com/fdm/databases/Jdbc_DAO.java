package com.fdm.databases;

import com.fdm.accounts.Account;
import com.fdm.accounts.SetupAnAccount;
import com.fdm.exceptions.BankTellerException;

public class Jdbc_DAO implements _AccountDatabase {

	private SetupAnAccount setupAccount = SetupAnAccount.INSTANCE;
	private QueryStrings queryStrings = new QueryStrings();
	private Jdbc_DTO dataTransferObject = new Jdbc_DTO();
	
	public int getHighestAccountNumber() throws BankTellerException {
		return dataTransferObject.retrieveHighestAccountNumberFromDatabase(queryStrings.getAccountWithHighestAccountNumber());
	}
	
	public void createAccount(Account account) throws BankTellerException {
		
		//Ensure that the newest account will have the highest account number
		account.setAccountNumber(getHighestAccountNumber()+1);		
		setupAccount.setAccountNumber(getHighestAccountNumber()+1);
		
		dataTransferObject.createAccountInDatabase(queryStrings.getCreateAccount(), account);
	}

	public Account retrieveAccount(int accountNumber) throws BankTellerException {
		
		return dataTransferObject.retrieveAccountFromDatabase(queryStrings.getRetrieveAccount(), accountNumber);
	}

	public void updateAccount(int accountNumber, double newBalance) throws BankTellerException {
		
		dataTransferObject.updateAccountInDatabase(queryStrings.getUpdateAccount(), accountNumber, newBalance);
	}
	
	public void deleteAccount(int accountNumber) throws BankTellerException {
		
		dataTransferObject.deleteAccountInDatabase(queryStrings.getDeleteAccount(), accountNumber);
	}

	public boolean accountExists(int accountNumber) throws BankTellerException {
		
		Account account = null;
		account = dataTransferObject.retrieveAccountFromDatabase(queryStrings.getRetrieveAccount(), accountNumber);
		return (null!=account);
	}

}
