package com.fdm.exceptions;

public class EmptyFieldException extends BankTellerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3005035286641101150L;
	private final static String MESSAGE = "A field was left empty";
	
	public String getErrorMsg() {
		return MESSAGE;
	}

}
