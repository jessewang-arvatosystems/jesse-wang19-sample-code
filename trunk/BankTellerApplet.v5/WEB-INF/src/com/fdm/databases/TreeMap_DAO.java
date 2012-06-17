package com.fdm.databases;

import java.util.TreeMap;

import com.fdm.accounts.Account;
import com.fdm.accounts.SetupAnAccount;

public class TreeMap_DAO extends TreeMap<Integer, Account> implements _AccountDatabase {

	private static final long serialVersionUID = 6372798306854440775L;
	private SetupAnAccount setupAccount = SetupAnAccount.INSTANCE;
	
	public void createAccount(Account account) {
		
		this.put(setupAccount.getAccountNumber(), account);
	}
	
	public Account retrieveAccount(int accountNumber){
		
		return this.get(accountNumber);
	}
	
	public void updateAccount(int accountNumber, double newBalance) {
		
		this.get(accountNumber).setBalance(newBalance);
	}
	
	public void deleteAccount(int accountNumber) {
		
		this.remove(accountNumber);
	}
	
	public boolean accountExists(int accountNumber) {
		
		return this.containsKey(accountNumber);
	}

	public int getHighestAccountNumber() {
		if (!this.isEmpty())
			return this.lastKey();
		else
			return 1;
	}


}
