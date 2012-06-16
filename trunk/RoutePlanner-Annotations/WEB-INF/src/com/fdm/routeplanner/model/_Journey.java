package com.fdm.routeplanner.model;

import java.util.List;

public interface _Journey {
	
	/**
	 * @return List of Leg Messages
	 */
	
	public abstract List<String> getMessage();
	
	/**
	 * Add new Leg Message to the Route.
	 * @param message
	 */
	
	public abstract void addMessage(String message);
	
	public abstract void clearJourney();

	public abstract void setLowestZone(int lowestZone);

	public abstract int getLowestZone();

}
