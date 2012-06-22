package com.fdm.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.junit.Test;

import com.fdm.webTeller.WebTellerActionLoader;

public final class TestProperties {

	private final String 	DATABASE = "ACCOUNT_DATA",
							ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER = "SELECT * FROM " + DATABASE + " WHERE ACCOUNT_NUMBER=" +
						 		"(SELECT MAX(ACCOUNT_NUMBER) FROM " + DATABASE + ")",
						 	CREATE_ACCOUNT = "INSERT INTO " + DATABASE + " VALUES(?, ?, ?, ?, ?)",
						 	RETRIEVE_ACCOUNT = "SELECT * FROM " + DATABASE + " WHERE ACCOUNT_NUMBER=?",
						 	UPDATE_ACCOUNT = "UPDATE " + DATABASE + " SET BALANCE=? WHERE ACCOUNT_NUMBER=?",
						 	DELETE_ACCOUNT = "DELETE FROM " + DATABASE + " WHERE ACCOUNT_NUMBER=?",
							USER_NAME = "jessewang",
							PASSWORD = "JW478Ca5",
							DRIVER_TYPE = "thin",
							SERVER_NAME = "oracle.fdmgroup.com",
							DATABASE_NAME = "campus";
	private String 	queryFile = "QueryFile.txt",
					jdbcConnectionInfoFile = "JDBCConnectionInfoFile.txt";
	private int	PORT = 1521;
	
	
	Properties 	outputProperties = new Properties(),
				inputProperties = new Properties();
	
	@Test
	public void testQueryStrings() {
		outputProperties.setProperty("ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER", ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER);
		outputProperties.setProperty("CREATE_ACCOUNT", CREATE_ACCOUNT);
		outputProperties.setProperty("RETRIEVE_ACCOUNT", RETRIEVE_ACCOUNT);
		outputProperties.setProperty("UPDATE_ACCOUNT", UPDATE_ACCOUNT);
		outputProperties.setProperty("DELETE_ACCOUNT", DELETE_ACCOUNT);
		
		OutputStream out = null;
		File file = new File(queryFile);
		
		try {
			out = new FileOutputStream(file);
			outputProperties.store(out, "Query Strings");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out!= null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		InputStream in = null;
		
		try {
			in = new FileInputStream(queryFile);
			inputProperties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		assertEquals(ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER, inputProperties.getProperty("ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER"));
		assertEquals(CREATE_ACCOUNT, inputProperties.getProperty("CREATE_ACCOUNT"));
		assertEquals(RETRIEVE_ACCOUNT, inputProperties.getProperty("RETRIEVE_ACCOUNT"));
		assertEquals(UPDATE_ACCOUNT, inputProperties.getProperty("UPDATE_ACCOUNT"));
		assertEquals(DELETE_ACCOUNT, inputProperties.getProperty("DELETE_ACCOUNT"));
	}
	
	@Test
	public void testUsernameAndPasswordForJDBC() {
		outputProperties.setProperty("USER_NAME", USER_NAME);
		outputProperties.setProperty("PASSWORD", PASSWORD);
		outputProperties.setProperty("DRIVER_TYPE", DRIVER_TYPE);
		outputProperties.setProperty("SERVER_NAME", SERVER_NAME);
		outputProperties.setProperty("PORT", Integer.toString(PORT));
		outputProperties.setProperty("DATABASE_NAME", DATABASE_NAME);
		
		OutputStream out = null;
		
		try {
			out = new FileOutputStream(new File(jdbcConnectionInfoFile));
			outputProperties.store(out, "JDBC Connection Info");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out!= null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		InputStream in = null;
		
		try {
			in = new FileInputStream(jdbcConnectionInfoFile);
			inputProperties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		assertEquals(USER_NAME, inputProperties.getProperty("USER_NAME"));
		assertEquals(PASSWORD, inputProperties.getProperty("PASSWORD"));
		assertEquals(DRIVER_TYPE, inputProperties.getProperty("DRIVER_TYPE"));
		assertEquals(SERVER_NAME, inputProperties.getProperty("SERVER_NAME"));
		assertEquals(PORT, Integer.parseInt(inputProperties.getProperty("PORT")));
		assertEquals(DATABASE_NAME, inputProperties.getProperty("DATABASE_NAME"));
	}
	
	@Test
	public void testWebTellerActions() {
		outputProperties.setProperty("ACTION_CLASS_1", "CreateCurrentAccountAction");
		outputProperties.setProperty("ACTION_CLASS_2", "CreateSavingsAccountAction");
		outputProperties.setProperty("ACTION_CLASS_3", "DepositAccountAction");
		outputProperties.setProperty("ACTION_CLASS_4", "WithdrawAccountAction");
		outputProperties.setProperty("ACTION_CLASS_5", "ListAllAccountsAction");
		outputProperties.setProperty("ACTION_CLASS_6", "CloseAccountAction");
		outputProperties.setProperty("ACTION_CLASS_7", "ViewAccountAction");
		
		OutputStream out = null;
		
		try {
			out = new FileOutputStream(new File("BankTellerActions.txt"));
			outputProperties.store(out, "Bank Teller Actions");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out!= null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		InputStream in = null;
		
		try {
			in = new FileInputStream("BankTellerActions.txt");
			inputProperties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		assertEquals("CreateCurrentAccountAction", inputProperties.getProperty("ACTION_CLASS_1"));
		assertEquals("CreateSavingsAccountAction", inputProperties.getProperty("ACTION_CLASS_2"));
		assertEquals("DepositAccountAction", inputProperties.getProperty("ACTION_CLASS_3"));
		assertEquals("WithdrawAccountAction", inputProperties.getProperty("ACTION_CLASS_4"));
		assertEquals("ListAllAccountsAction", inputProperties.getProperty("ACTION_CLASS_5"));
		assertEquals("CloseAccountAction", inputProperties.getProperty("ACTION_CLASS_6"));
		assertEquals("ViewAccountAction", inputProperties.getProperty("ACTION_CLASS_7"));
	}
	
	@Test
	public void testWebTellerActionLoader() {
		Map<Integer, String> actions = new TreeMap<Integer, String>();
		WebTellerActionLoader loader = new WebTellerActionLoader();
		
		loader.loadActions(actions);
		
		assertEquals("CreateCurrentAccountAction", actions.get(1));
		assertEquals("CreateSavingsAccountAction", actions.get(2));
		assertEquals("DepositAccountAction", actions.get(3));
		assertEquals("WithdrawAccountAction", actions.get(4));
		assertEquals("ListAllAccountsAction", actions.get(5));
		assertEquals("CloseAccountAction", actions.get(6));
		assertEquals("ViewAccountAction", actions.get(7));
	}
}
