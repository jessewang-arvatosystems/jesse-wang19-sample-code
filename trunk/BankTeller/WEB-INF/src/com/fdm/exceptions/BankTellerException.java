package com.fdm.exceptions;

public abstract class BankTellerException extends Exception {

	private static final long serialVersionUID = -7096564777900402806L;
	public abstract String getErrorMsg();
	
}
