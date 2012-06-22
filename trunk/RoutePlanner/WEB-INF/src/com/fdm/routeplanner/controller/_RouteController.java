package com.fdm.routeplanner.controller;

import java.util.Set;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model._Journey;
import com.fdm.routeplanner.model.network._Node;

public interface _RouteController {

	/**
	 * Accesses RoutePlannerModelFacade to get data transfer object
	 * 
	 * @param startStation
	 * @param endStation
	 * @return _Journey
	 * @throws RoutePlannerException
	 */
	public abstract _Journey getRoute(String startStation,
			String endStation) throws RoutePlannerException;

	/**
	 * Accesses RoutePlannerModelFacade to get list of nodes (strings)
	 * 
	 * @return Set<_Node>
	 * @throws RoutePlannerException
	 */
	public abstract Set<_Node> getStationList()
			throws RoutePlannerException;
	
	/**
	 * Gets list of all station names for view
	 * 
	 * @return String[]
	 * @throws RoutePlannerException
	 */
	public abstract String[] getStationNames() throws RoutePlannerException;

	public abstract _Journey getOneRoute(String startStation, String endStation)
			throws RoutePlannerException;

}
