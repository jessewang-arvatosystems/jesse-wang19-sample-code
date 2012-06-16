package com.fdm.exceptions;

public class InvalidAccountWithdrawalException extends BankTellerException {

	private static final long serialVersionUID = 422586438135796077L;
	private final static String MESSAGE = "The withdrawal amount was too high.";
	
	public String getErrorMsg() {
		return MESSAGE;
	}

}
