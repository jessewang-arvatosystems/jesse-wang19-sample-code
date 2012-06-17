package com.fdm.exceptions;

public class InvalidNumberFormatException extends BankTellerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1617945205933478474L;
	private final static String MESSAGE = "A form field contain characters when expecting numeric values";
	
	public String getErrorMsg() {
		return MESSAGE;
	}

}
