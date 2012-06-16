package com.fdm.test;

import org.junit.Before;
import org.junit.Test;

import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;
import com.fdm.exceptions.CannotCloseAccountException;
import com.fdm.exceptions.InvalidAccountNumberException;
import com.fdm.exceptions.InvalidAccountWithdrawalException;
import com.fdm.exceptions.NegativeNumberException;

public final class TestExceptions {

	private BankGateway gateway;
	
	private int accountNumber1 = 1,
				accountNumber2 = 2,
				accountNumber3 = 3,
				accountNumber4 = 4,
				negativeNumber = -1;
	
	private String name = "testbug";
	private double 	initialDeposit = 123.12,
					newBalance = 200,
					overdraftLimit = 200.11,
					tooHighWithdrawal = 10000;
	
	@Before
	public void initialize() {
		gateway = BankGateway.INSTANCE;
	}
	
//	@Test (expected = InvalidDatabaseTypeException.class)
//	public void testInvalidDatabaseException() throws BankTellerException {
//		gateway.createCurrentAccount(name, initialDeposit, overdraftLimit);
//	}
	
	// Only for JDBC
//	@Test (expected = InvalidQueryStringsFileException.class)
//	public void testInvalidQueryStringsException() throws BankTellerException {
//		gateway.createCurrentAccount(name, initialDeposit, overdraftLimit);
//	}
	
	@Test (expected = InvalidAccountNumberException.class)
	public void testInvalidAccountNumberWithdrawalException() throws InvalidAccountNumberException, NegativeNumberException, BankTellerException {
		gateway.makeWithdrawal(newBalance, accountNumber1);
	}
	
	@Test (expected = InvalidAccountNumberException.class)
	public void testInvalidAccountNumberDepositException() throws InvalidAccountNumberException, NegativeNumberException, BankTellerException {
		gateway.makeDeposit(newBalance, accountNumber1);
	}
	
	@Test (expected = InvalidAccountNumberException.class)
	public void testInvalidAccountNumberViewException() throws InvalidAccountNumberException, NegativeNumberException, BankTellerException {
		gateway.viewAccountSummary(accountNumber1);
	}
	
	@Test (expected = InvalidAccountWithdrawalException.class)
	public void testInvalidWithdrawalException() throws InvalidAccountNumberException, NegativeNumberException, BankTellerException {
		try {
			// To remove previously created account
			gateway.makeWithdrawal(initialDeposit, accountNumber1);
			gateway.closeAccount(accountNumber1);
		} catch (BankTellerException e) {}
		
		gateway.createCurrentAccount(name, initialDeposit, overdraftLimit);
		gateway.makeWithdrawal(tooHighWithdrawal, accountNumber1);
	}
	
	@Test (expected = CannotCloseAccountException.class)
	public void testCannotCloseAccountException() throws BankTellerException {
		try {
			// To remove previously created account
			gateway.makeWithdrawal(initialDeposit, accountNumber2);
			gateway.closeAccount(accountNumber2);
		} catch (BankTellerException e) {}
		
		gateway.createSavingsAccount(name, initialDeposit);
		gateway.closeAccount(accountNumber2);
	}
	
	@Test (expected = NegativeNumberException.class) 
	public void testNegativeInitialDeposit() throws BankTellerException {
		gateway.createSavingsAccount(name, negativeNumber);
	}
	
	@Test (expected = NegativeNumberException.class) 
	public void testNegativeOverdraftLimit() throws BankTellerException {
		gateway.createCurrentAccount(name, initialDeposit, negativeNumber);
	}
	
	@Test (expected = NegativeNumberException.class) 
	public void testNegativeWithdrawal() throws InvalidAccountNumberException, NegativeNumberException, BankTellerException {
		try {
			// To remove previously created account
			gateway.makeWithdrawal(initialDeposit, accountNumber3);
			gateway.closeAccount(accountNumber3);
		} catch (BankTellerException e) {}
		
		gateway.createCurrentAccount(name, initialDeposit, overdraftLimit);
		gateway.makeWithdrawal(negativeNumber, accountNumber3);
	}
	
	@Test (expected = NegativeNumberException.class) 
	public void testNegativeDeposit() throws InvalidAccountNumberException, NegativeNumberException, BankTellerException {
		try {
			// To remove previously created account
			gateway.makeWithdrawal(initialDeposit, accountNumber4);
			gateway.closeAccount(accountNumber4);
		} catch (BankTellerException e) {}
		
		gateway.createCurrentAccount(name, initialDeposit, overdraftLimit);
		gateway.makeDeposit(negativeNumber, accountNumber4);
	}
	
}