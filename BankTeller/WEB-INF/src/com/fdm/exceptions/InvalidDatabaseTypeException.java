package com.fdm.exceptions;

public class InvalidDatabaseTypeException extends BankTellerException {

	private static final long serialVersionUID = 3251734439396736384L;
	private final static String MESSAGE = "Error with BankTellerDatabaseTypeFile.txt. File does not exist or has invalid database type";
	
	public String getErrorMsg() {
		return MESSAGE;
	}

}
