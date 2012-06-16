package com.fdm.routeplanner.model.network;

import com.fdm.routeplanner.exception.RoutePlannerException;


public interface _MapBuilder {
	
	/**
	 * Instantiate a route map
	 * 
	 * @return _RouteMap
	 * @throws RoutePlannerException
	 */
	public abstract _RoutesMap buildRoutesMap() throws RoutePlannerException;
}
