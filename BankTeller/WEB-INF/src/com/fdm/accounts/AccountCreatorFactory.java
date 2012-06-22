package com.fdm.accounts;

public enum AccountCreatorFactory {
	
	INSTANCE;
	
	private static final String PACKAGE_NAME = "com.fdm.accounts.";
	
	public Account createAccount(String accountType) {
		
		Account anAccount = null;
		
		try {
			anAccount = (Account) Class.forName(PACKAGE_NAME + accountType).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return anAccount;
	}
	
}
