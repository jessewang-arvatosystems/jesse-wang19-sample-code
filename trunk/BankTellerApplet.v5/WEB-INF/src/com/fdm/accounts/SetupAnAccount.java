package com.fdm.accounts;

import com.fdm.exceptions.NegativeNumberException;
import com.fdm.helper.ExceptionHelper;

public enum SetupAnAccount {

	INSTANCE;
	
	private AccountCreatorFactory accountFactory = AccountCreatorFactory.INSTANCE;
	private static int accountNumberCount = 0;
	
	
	public Account setupAccount(AccountType accountType, String name, double initialDeposit, double overdraft) throws NegativeNumberException {	
		
		Account account = accountFactory.createAccount(accountType.getEnumValue());
		account.setAccountType(accountType.name());
		account.setName(name);
		setInitialBalance(account, initialDeposit);
		setOverdraft(account, overdraft);
		assignUniqueAccountNumber(account);
		
		return account;
	}
	
	private void setInitialBalance(Account account, double initialDeposit) throws NegativeNumberException {
		
		if (ExceptionHelper.isValidNumber(initialDeposit))
			account.setBalance(initialDeposit);
	}
	
	private void setOverdraft(Account account, double overdraft) throws NegativeNumberException {
		
		if (ExceptionHelper.isValidNumber(overdraft))
			account.setOverdraft(overdraft);
	}
	
	private synchronized void assignUniqueAccountNumber(Account anAccount) throws NegativeNumberException {
		
		accountNumberCount++;
		anAccount.setAccountNumber(accountNumberCount);
	}
	
	public int getAccountNumber() {
		
		return accountNumberCount;
	}
	
	public void setAccountNumber(int accountNumber) {
		
		accountNumberCount = accountNumber;
	}
	
}
