package com.fdm.databases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class QueryStrings {
	
	private final static String QUERY_FILE = "resources/BankTellerQueryFile.txt";
	private Properties queryProperties = new Properties();
	
	public final String ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER,
						CREATE_ACCOUNT,
						RETRIEVE_ACCOUNT,
						UPDATE_ACCOUNT,
						DELETE_ACCOUNT;

	public Properties getQueryProperties() {
		
		return queryProperties;
	}

	public String getAccountWithHighestAccountNumber() {
		
		return ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER;
	}

	public String getCreateAccount() {
		
		return CREATE_ACCOUNT;
	}

	public String getRetrieveAccount() {
		
		return RETRIEVE_ACCOUNT;
	}

	public String getUpdateAccount() {
		
		return UPDATE_ACCOUNT;
	}

	public String getDeleteAccount() {
		
		return DELETE_ACCOUNT;
	}

	public QueryStrings() {
		
		getQueryStrings();
		ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER = queryProperties.getProperty("ACCOUNT_WITH_HIGHEST_ACCOUNT_NUMBER");
		CREATE_ACCOUNT = queryProperties.getProperty("CREATE_ACCOUNT");
		RETRIEVE_ACCOUNT = queryProperties.getProperty("RETRIEVE_ACCOUNT");
		UPDATE_ACCOUNT = queryProperties.getProperty("UPDATE_ACCOUNT");
		DELETE_ACCOUNT = queryProperties.getProperty("DELETE_ACCOUNT");
	}
	
	public void getQueryStrings() {
		
		InputStream in = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream(QUERY_FILE);
			queryProperties.load(in);
		} 
		catch (FileNotFoundException e) {} 
		catch (IOException e) {} 
		finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
}