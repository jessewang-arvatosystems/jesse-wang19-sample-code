package com.fdm.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fdm.accounts.Account;
import com.fdm.accounts.AccountType;
import com.fdm.accounts.SetupAnAccount;
import com.fdm.databases.ByteIO_DAO;
import com.fdm.databases.Jdbc_DAO;
import com.fdm.databases.TreeMap_DAO;
import com.fdm.databases._AccountDatabase;
import com.fdm.exceptions.BankTellerException;


public final class TestDatabases {
	
	private SetupAnAccount setupAnAccount = SetupAnAccount.INSTANCE;

	@SuppressWarnings("unused")
	private _AccountDatabase 	tmaDB = new TreeMap_DAO(),
								bIO = new ByteIO_DAO(),
								jDBC = new Jdbc_DAO();
	
	private int accountNumber1 = 1,
				accountNumber2 = 2,
				accountNumber3 = 3;
	private String name = "testbug";
	private double 	initialDeposit = 123.12,
					newBalance = 200,
					overdraft = 200.11;
	private Account currentAccount, savingsAccount;
	
	//** Test one database at a time **//
	
	@Test
	public void testAccountTreeMap() {
		testDatabase(tmaDB);
	}

//	@Test
//	public void testByteIO() {
//		testDatabase(bIO);
//	}
	
//	@Test
//	public void testJDBC() {
//		testDatabase(jDBC);
//	}
	
	public void testDatabase(_AccountDatabase database) {
		try {
			setupAnAccount.setAccountNumber(0);
			
			currentAccount = setupAnAccount.setupAccount(AccountType.CURRENT, name, initialDeposit, overdraft);
			database.createAccount(currentAccount);
			database.updateAccount(accountNumber1, newBalance);
			assertTrue(newBalance == (database.retrieveAccount(accountNumber1).getBalance()));
			
			savingsAccount = setupAnAccount.setupAccount(AccountType.SAVINGS, name, initialDeposit, 0d);
			database.createAccount(savingsAccount);
			database.updateAccount(accountNumber2, newBalance);
			assertTrue(newBalance == (database.retrieveAccount(accountNumber2).getBalance()));
			
			currentAccount = setupAnAccount.setupAccount(AccountType.CURRENT, name, initialDeposit, overdraft);
			database.createAccount(currentAccount);
			database.updateAccount(accountNumber3, newBalance);
			assertTrue(newBalance == (database.retrieveAccount(accountNumber3).getBalance()));			
			
			database.deleteAccount(accountNumber1);
			database.deleteAccount(accountNumber2);			
			database.deleteAccount(accountNumber3);			
			
			assertFalse(database.accountExists(accountNumber1));
			assertFalse(database.accountExists(accountNumber2));
			assertFalse(database.accountExists(accountNumber3));
		} catch (BankTellerException e) {
			e.printStackTrace();
		}
	}

}
