package com.fdm.exceptions;

public class CannotCloseAccountException extends BankTellerException {

	private static final long serialVersionUID = 3251734439396736384L;
	private final static String MESSAGE = "Cannot close the account, there is a non-zero balance in the account.";
	
	public String getErrorMsg() {
		return MESSAGE;
	}

}
