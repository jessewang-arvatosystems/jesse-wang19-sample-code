package com.fdm.routeplanner.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.RoutePlannerModelFacade;
import com.fdm.routeplanner.model._Journey;
import com.fdm.routeplanner.model.network._Node;

@Repository("routeController")
@Scope("request")
class RouteController implements _RouteController {

	private final RoutePlannerModelFacade routePlannerModelFacade;
	
	@Autowired
	RouteController(@Value("#{routePlannerModelFacade}") RoutePlannerModelFacade gateway){
		this.routePlannerModelFacade = gateway;
	}
	
	@Override
	public final _Journey getRoute(String startStation, String endStation)
			throws RoutePlannerException {
		_Journey tempJourney = routePlannerModelFacade.getRoutes(startStation, endStation);
		return tempJourney;
	}
	@Override
	public final _Journey getOneRoute(String startStation, String endStation)
			throws RoutePlannerException {
		_Journey tempJourney = routePlannerModelFacade.getOneRoute(startStation, endStation);

		return tempJourney;
	}

	public final Set<_Node> getStationList() throws RoutePlannerException {
		return routePlannerModelFacade.getStations();
	}
	
	public final String[] getStationNames() throws RoutePlannerException {
		return routePlannerModelFacade.getStationNames();
	}
}
