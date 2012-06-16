package com.fdm.routeplanner.model.routebuilder;

import java.util.List;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.network._RoutesMap;
import com.fdm.routeplanner.model.network._Node;


public interface _RouteBuilder {

	public abstract void setRoutesMap(_RoutesMap routesMap);
	public abstract List<_Route> getRoutes(_Node node1, _Node node2) throws RoutePlannerException;
	
}