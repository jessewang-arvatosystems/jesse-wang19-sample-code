package com.fdm.accounts;

import java.io.Serializable;

import com.fdm.exceptions.InvalidAccountWithdrawalException;
import com.fdm.exceptions.NegativeNumberException;
import com.fdm.helper.MathHelper;

public abstract class Account implements Serializable{

	private static final long serialVersionUID = -7726394055345871249L;
	private int			accountNumber;
	protected double 	balance, overdraftLimit;
	private String		name,
						accountType;

	public abstract void makeWithdrawal(double withdrawal) throws NegativeNumberException, InvalidAccountWithdrawalException;
	public abstract void makeDeposit(double deposit) throws NegativeNumberException;
	
	public double getBalance() {
		return balance;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public String getName() {
		return name;
	}

	public void setBalance(double amount) {
		balance = MathHelper.roundToTwoDecimalPlaces(amount);
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setOverdraft(double overdraftLimit) {
			this.overdraftLimit = MathHelper.roundToTwoDecimalPlaces(overdraftLimit);
	}
	
	public double getOverdraft() {	
		return overdraftLimit;
	}

}
