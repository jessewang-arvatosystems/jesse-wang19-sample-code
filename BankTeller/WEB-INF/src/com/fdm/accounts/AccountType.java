package com.fdm.accounts;

public enum AccountType {
	
	CURRENT ("CurrentAccount"),
	SAVINGS ("SavingsAccount");
	
	private String enumValue;
	
	AccountType (String enumValue) {
        this.enumValue = enumValue;
    }

	public String getEnumValue() {
		return enumValue;
	}

}
