package com.fdm.exceptions;

public class InvalidAccountNumberException extends BankTellerException {

	private static final long serialVersionUID = 4655964700718013979L;
	private final static String MESSAGE = "The Account Number was not found.";
	
	public String getErrorMsg() {
		return MESSAGE;
	}

}
