package com.fdm.routeplanner.exception;

/**
 * Super exception
 * 
 * @author shifra.kastel
 *
 */
public class RoutePlannerException extends Exception {

	private static final long serialVersionUID = 1L;

	public RoutePlannerException(String message) {
		super(message);
	}

	public RoutePlannerException(Exception e, String message) {
		super(message, e);
	}

	public RoutePlannerException(Exception e) {
		super(e);
	}

}
