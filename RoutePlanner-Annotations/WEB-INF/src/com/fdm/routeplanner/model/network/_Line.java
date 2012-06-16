package com.fdm.routeplanner.model.network;


public interface _Line extends Comparable<_Line>{
	
	/**
	 * @return Line name
	 */
	public abstract String getName();
	
	/**
	 * @return hashcode of line
	 */
	public abstract int hashCode() ;
	
	/**
	 * Tests if line object belongs to route network
	 * 
	 * @param obj
	 * @return
	 */
	public abstract boolean equals(Object obj) ;
}
