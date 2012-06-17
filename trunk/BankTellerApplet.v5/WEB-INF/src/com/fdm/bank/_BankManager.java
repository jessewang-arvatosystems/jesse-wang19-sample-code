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

import java.util.List;

import com.fdm.exceptions.BankTellerException;

/**
 * @author keith.dauris
 *
 */
public interface _BankManager {
	
	/**
	 * Creates a new current account and then returns details to user
	 * @param name
	 * @param amount
	 * @param overdraftLimit
	 * @return The value object holding summary details for this account
	 * @throws BankTellerException
	 */
	public BankAccountOutputVO createNewCurrentAccount(String name, double amount, double overdraftLimit) throws BankTellerException;

	/**
	 * 
	 * @param name
	 * @param amount
	 * @return The value object holding summary details for this account
	 * @throws BankTellerException
	 */
	public BankAccountOutputVO createNewSavingsAccount(String name, double amount) throws BankTellerException;

	/**
	 * 
	 * @param accountNumber
	 * @param amount
	 * @return The value object holding summary details for this account
	 * @throws NumberFormatException 
	 * @throws BankTellerException
	 */
	public BankAccountOutputVO makeDeposit(String accountNumber, double amount) throws NumberFormatException, BankTellerException;

	/**
	 * 
	 * @param accountNumber
	 * @param amount
	 * @return The value object holding summary details for this account
	 * @throws NumberFormatException 
	 * @throws BankTellerException
	 */
	public BankAccountOutputVO makeWithdrawal(String accountNumber, double amount) throws NumberFormatException, BankTellerException;

	/**
	 * 
	 * @return The list of all accounts currently held in the bank. This list  
	 * must consist of objects of type BankAccountOutputVO. 
	 * @throws BankTellerException
	 */
	public List<BankAccountOutputVO> listAllAccounts() throws BankTellerException;

	/**
	 * 
	 * @param accountNumber
	 * @return The value object holding summary details for this account
	 * @throws NumberFormatException 
	 * @throws BankTellerException
	 */
	public BankAccountOutputVO ViewAccountSummary(String accountNumber) throws NumberFormatException, BankTellerException;

	/**
	 * 
	 * @param accountNumber
	 * @return The value object holding summary details for this account
	 * @throws NumberFormatException
	 */
	public BankAccountOutputVO closeAcount(String accountNumber) throws NumberFormatException, BankTellerException;
}
