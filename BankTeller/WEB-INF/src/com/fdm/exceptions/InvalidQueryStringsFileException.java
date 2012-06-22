package com.fdm.exceptions;

public class InvalidQueryStringsFileException extends BankTellerException {

	private static final long serialVersionUID = -3118606671299384480L;
	private final static String MESSAGE = "Error with BankTellerQueryFile.txt. File does not exist or has invalid query strings";
	
	public String getErrorMsg() {
		return MESSAGE;
	}

}
