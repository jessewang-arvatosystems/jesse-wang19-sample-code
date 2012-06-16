package com.fdm.routeplanner.model.routebuilder;

import java.util.List;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.network._Edge;
import com.fdm.routeplanner.model.network._Node;
import com.fdm.routeplanner.model.network._RoutesMap;


public interface _RouteCalculator {
	
	/**
	 * Setter for the routesMap for dependency injection
	 * @param routesMap
	 */
	public abstract void setMap(_RoutesMap routesMap);
	
	/**
	 * Clears member variables and then calls the Dijkstra engine with the start and end nodes
	 * @param startNode
	 * @param destinationNode
	 * @throws RoutePlannerException
	 */
	public abstract void execute(_Node startNode, _Node destinationNode) throws RoutePlannerException;
	
    /**
     * Creates a list containing a list of edges. Each edge list is the route a person will take 
     * from the end destination to the start destination in the lowest number of stops and
     * transfers
     * @param end last station
     * @return list of all the possible destinations starting from that end node
     * @throws RoutePlannerException
     */	
	public abstract List<List<_Edge>> getPredecessor(_Node destinationNode) throws RoutePlannerException;
}
