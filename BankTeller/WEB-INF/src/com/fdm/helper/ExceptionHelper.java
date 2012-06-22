package com.fdm.helper;

import com.fdm.exceptions.BankTellerException;
import com.fdm.exceptions.EmptyFieldException;
import com.fdm.exceptions.InvalidAccountNumberException;
import com.fdm.exceptions.InvalidNumberFormatException;
import com.fdm.exceptions.NegativeNumberException;

public final class ExceptionHelper {

	public static boolean isValidNumber(double number) throws NegativeNumberException {
		
		if (number >= 0d)
			return true;
		else
			throw new NegativeNumberException();
	}
	
	public static boolean isValidNumber(int number) throws NegativeNumberException {
		
		if (number >= 0)
			return true;
		else
			throw new NegativeNumberException();
	}
	
	public static void handleInvalidAccountNumber(int accountNumber) throws NegativeNumberException, InvalidAccountNumberException {
		
		if (accountNumber < 0)
			throw new NegativeNumberException();
		else
			throw new InvalidAccountNumberException();
	}
	
	public static void verifyNumberField(String value) throws BankTellerException {
		handleEmptyField(value);
		handleInvalidCurrency(value);
	}
	
	private static void handleInvalidCurrency(String value) throws InvalidNumberFormatException {
		
		if (!value.replaceAll("[0-9]", "").isEmpty()) {
			throw new InvalidNumberFormatException();
		}
		
	}
	
	public static void handleEmptyField(String value) throws EmptyFieldException {
		
		if (value.replaceAll(" ", "").isEmpty()) {
			throw new EmptyFieldException();
		}
	}
	
}
