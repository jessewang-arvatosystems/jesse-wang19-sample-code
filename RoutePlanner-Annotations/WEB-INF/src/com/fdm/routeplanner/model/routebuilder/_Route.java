package com.fdm.routeplanner.model.routebuilder;

import java.util.List;

import com.fdm.routeplanner.model.network._Node;


public interface _Route {

	/**
	 * @return List<_Leg>
	 */
	public abstract List<_Leg> getListOfLegs();
	
	
	/**
	 * 
	 * @return route's start node
	 */
	public abstract _Node getRouteStartNode(); 

	/**
	 * @return route's end node
	 */
	public abstract _Node getRouteEndNode();	
	
	/**
	 * @return number of stops in route
	 */
	public abstract int getNumberOfStops();	
	
	/**
	 * Add leg to route
	 * 
	 * @param leg
	 */
	public abstract void addLeg(_Leg leg);		
	
	/**
	 * @return number of legs in route
	 */
	public abstract int getNumberOfLegs();
	
	/**
	 * Compare two routes
	 * 
	 * @param r
	 * @return
	 */
	public abstract int compareTo(_Route r);
	public abstract void setLowestZone(int lowestZone);
	public abstract int getLowestZone();
				
	
}
