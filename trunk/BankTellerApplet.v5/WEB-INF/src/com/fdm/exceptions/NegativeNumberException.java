package com.fdm.exceptions;

public class NegativeNumberException extends BankTellerException {

	private static final long serialVersionUID = -1185054922092539854L;
	private final static String MESSAGE = "Cannot enter negative values.";
	
	public String getErrorMsg() {
		return MESSAGE;
	}
}
