/*
**********************************************************************
* This code and its derivatives belong to FDM Group PLC and may not be
* copied,reproduced, amended or used in any way without permission
* from FDM group PLC
**********************************************************************
* Current Version
* ===============
* Revision: v1.0
* Date/time: 16 Jan 2007
**********************************************************************
*/
package com.fdm.bank;

import java.util.List;

import com.fdm.exceptions.BankTellerException;


public class BankManager implements _BankManager
{
	private BankGateway gateway = BankGateway.INSTANCE;
	
	public BankAccountOutputVO ViewAccountSummary(String accountNumber) throws BankTellerException, NumberFormatException
	{
		return gateway.viewAccountSummary(Integer.parseInt(accountNumber));
	}

	public BankAccountOutputVO closeAcount(String accountNumber) throws NumberFormatException, BankTellerException
	{
		return gateway.closeAccount(Integer.parseInt(accountNumber));
	}

	public BankAccountOutputVO createNewCurrentAccount(String name,
			double amount, double overdraftLimit) throws BankTellerException
	{
		return gateway.createCurrentAccount(name, amount, overdraftLimit);
	}

	public BankAccountOutputVO createNewSavingsAccount(String name,
			double amount) throws BankTellerException
	{
		return gateway.createSavingsAccount(name, amount);
	}

	public List<BankAccountOutputVO> listAllAccounts() throws BankTellerException
	{
		return gateway.listAllAccounts();
	}

	public BankAccountOutputVO makeDeposit(String accountNumber, double amount) throws NumberFormatException, BankTellerException
	{
		return gateway.makeDeposit(amount, Integer.parseInt(accountNumber));
	}

	public BankAccountOutputVO makeWithdrawal(String accountNumber,
			double amount) throws NumberFormatException, BankTellerException
	{
		return gateway.makeWithdrawal(amount, Integer.parseInt(accountNumber));
	}

}