package com.fdm.databases;

import com.fdm.accounts.Account;
import com.fdm.exceptions.BankTellerException;

public interface _AccountDatabase {
	
	public void createAccount(Account account) throws BankTellerException;
	public Account retrieveAccount(int accountNumber) throws BankTellerException;
	public void updateAccount(int accountNumber, double newBalance) throws BankTellerException;
	public void deleteAccount(int accountNumber) throws BankTellerException;
	public boolean accountExists(int accountNumber) throws BankTellerException;
	public int getHighestAccountNumber() throws BankTellerException;
}