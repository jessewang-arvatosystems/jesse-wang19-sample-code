package com.fdm.routeplanner.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.network._MapBuilder;
import com.fdm.routeplanner.model.network._Node;
import com.fdm.routeplanner.model.network._RoutesMap;
import com.fdm.routeplanner.model.routebuilder._Route;
import com.fdm.routeplanner.model.routebuilder._RouteBuilder;

@Service("routePlannerModelFacade")
@Scope("request")
public class RoutePlannerModelFacade {

	private final _RoutesMap routesMap;
	private final _RouteBuilder routeBuilder;
	private final _JourneyBuilder journeyBuilder;

	/**
	 * Constructor for dependency injection for ModelFacade
	 * 
	 * @param edgeMapBuilder
	 * @param routesMap
	 * @param routeBuilder
	 * @param journeyBuilder
	 * @throws RoutePlannerException
	 */
	@Autowired
	public RoutePlannerModelFacade(@Value("#{edgeMapBuilder}") _MapBuilder mapBuilder, 
			@Value("#{routeBuilder}") _RouteBuilder routeBuilder, 
			@Value("#{journeyBuilder}") _JourneyBuilder journeyBuilder)
			throws RoutePlannerException {
		this.routeBuilder = routeBuilder;
		this.journeyBuilder = journeyBuilder;
		this.routesMap = mapBuilder.buildRoutesMap();
	}

	/**
	 * Creates and returns data transfer object from Model
	 * 
	 * @param startNodeString
	 * @param endNodeString
	 * @return Journey
	 * @throws RoutePlannerException
	 */
	public final _Journey getRoutes(String startNodeString, String endNodeString)
			throws RoutePlannerException {

		_Node endNode = null, 
			  startNode = null;
		
		routeBuilder.setRoutesMap(routesMap);
		Set<_Node> allStations = getStations();
		Iterator<_Node> iterator = allStations.iterator();

		while (iterator.hasNext()) {
			_Node station = iterator.next();
			if (station.getName().equalsIgnoreCase(startNodeString))
				startNode = station;
			if (station.getName().equalsIgnoreCase(endNodeString))
				endNode = station;
		}
		
		List<_Route> listRoutes = routeBuilder.getRoutes(startNode, endNode);
		startNode = null;
		endNode = null;

		return journeyBuilder.createMessages(listRoutes);
	}

	
	/**
	 * Creates and returns a _Journey object with the text for one Route as well as
	 * information for the zone number cast as an integer. 
	 * 
	 * @return Journey
	 * @throws RoutePlannerException
	 */
	public final _Journey getOneRoute(String startNodeString, String endNodeString)
	throws RoutePlannerException {

		_Node endNode = null, startNode = null;

		routeBuilder.setRoutesMap(routesMap);
		Set<_Node> allStations = getStations();
		Iterator<_Node> iterator = allStations.iterator();

		while (iterator.hasNext()) {
			_Node station = iterator.next();
			if (station.getName().equalsIgnoreCase(startNodeString))
				startNode = station;
			if (station.getName().equalsIgnoreCase(endNodeString))
				endNode = station;
		}

		List<_Route> listRoutes = routeBuilder.getRoutes(startNode, endNode);
		startNode = null;
		endNode = null;

		return journeyBuilder.createOneMessage(listRoutes.get(0));
}
	/**
	 * Gets list of nodes from Model
	 * 
	 * @return Set<_Node>
	 * @throws RoutePlannerException
	 */
	public final Set<_Node> getStations() throws RoutePlannerException {
		return routesMap.getNodes();
	}
	
	/**
	 * Sets list of node objects to String array
	 * 
	 * @return String[] 
	 * @throws RoutePlannerException
	 */
	public final String[] getStationNames() throws RoutePlannerException {
		Set<_Node> stationSet =  getStations();
		String[] returnStringArray = new String[stationSet.size()];
		List<String> s = new ArrayList<String>();
		int i = 0;
		for(_Node n : stationSet){
			s.add(n.getName());
		}
		Collections.sort(s);
		for(String st: s){
			returnStringArray[i]=st;
			i++;
		}
		return returnStringArray;
	}

}
