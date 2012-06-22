package com.fdm.databases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum SelectDatabase {

	INSTANCE;
	
	private final static String DATABASE_TYPE_FILE = "resources/BankTellerDatabaseTypeFile.txt",
			PACKAGE_NAME = "com.fdm.databases.";
	private Properties inputProperties = new Properties();
	
	public _AccountDatabase selectDatabaseType() {
		
		InputStream in = null;
		_AccountDatabase database = null;
		
		try {
			in = getClass().getClassLoader().getResourceAsStream(DATABASE_TYPE_FILE);
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
		
		try {
			database = (_AccountDatabase) Class.forName(PACKAGE_NAME + inputProperties.getProperty("ACCOUNT_TYPE") + "_DAO").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return database;
	}
	
}