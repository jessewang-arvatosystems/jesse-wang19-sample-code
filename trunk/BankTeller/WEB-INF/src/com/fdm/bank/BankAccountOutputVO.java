/*
 **********************************************************************
 * This code and its derivatives belong to FDM Group PLC and may not be   
 * copied,reproduced, amended or used in any way without permission   
 * from FDM group PLC
 **********************************************************************
 * Current Version
 * ===============
 * Revision:  1.0
 * Date/time: 15/01/2007
 **********************************************************************
 */

package com.fdm.bank;

/**
 * Immutable class that holds bank account data passed to the teller package.
 * 
 * @author keith.dauris
 *
 */
public final class BankAccountOutputVO {

	private final String accountNumber;
	private final String accountType;
	private final String name;
	private final String balance;
	private final String overdraft;

	public BankAccountOutputVO(String accountNumber, String accountType, String name, String overdraft, String balance)
	{
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.name = name;
		this.overdraft = overdraft;
		this.balance = balance;
	}

	public String getAccountNumber()
	{
		return accountNumber;
	}

	public String getBalance()
	{
		return balance;
	}

	public String getName()
	{
		return name;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public String getOverdraft() 
	{
		return overdraft;
	}
}
