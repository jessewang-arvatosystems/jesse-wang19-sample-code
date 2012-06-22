package com.fdm.routeplanner.exception;

/**
 * Thrown when data cannot be read
 * 
 * @author shifra.kastel
 *
 */
public class InvalidNetWorkException extends RoutePlannerException {

	private static final long serialVersionUID = 1L;

	public InvalidNetWorkException(String message) {
		super(message);
	}

	public InvalidNetWorkException(Exception e, String message) {
		super(e, message);
	}

	public InvalidNetWorkException(Exception e) {
		super(e);
	}
}
