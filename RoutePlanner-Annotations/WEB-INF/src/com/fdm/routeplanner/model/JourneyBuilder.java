package com.fdm.routeplanner.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.model.routebuilder._Leg;
import com.fdm.routeplanner.model.routebuilder._Route;

@Service("journeyBuilder")
@Scope("request")
class JourneyBuilder implements _JourneyBuilder{

	private final _Journey journey;
	private static final Logger myLogger = Logger.getLogger("Router");
	
	/**
	 * @param journey
	 */
	@Autowired
	JourneyBuilder(@Value("#{journey}") _Journey journey) {
		this.journey = journey;
	}
	
	public final _Journey createMessages(List<_Route> allRoutes) {
		int routeIndex=0;
		journey.clearJourney();
		myLogger.info("Number of Routes: " + allRoutes.size() +"|"+ getStopDetails(allRoutes.get(0)) + 
				"|" + getTransferDetails(allRoutes.get(0)));
		for(_Route route : allRoutes){
			if(route != null){ 
			routeIndex++;
			journey.addMessage("Route Number [" + routeIndex + "]<br/>"
					+ "------<br/>"
					+ getRouteDetail(route) + "<br/>"
					+ getStopDetails(route) + "<br/>"
					+ getTransferDetails(route) + "<br/>"
					+ "====================================================<br/>");
			}
		}
		return journey;
	}
	
	public final _Journey createOneMessage(_Route route){
		journey.clearJourney();
			if(route != null){    
				journey.addMessage(getRouteDetail(route) + "<br/>"
						+ getStopDetails(route) + "<br/>"
						+ getTransferDetails(route) + "<br/>");
				journey.setLowestZone(route.getLowestZone());
			}
		return journey;
	}
	
	private final String getRouteDetail(_Route output){
		List<_Leg> legList = output.getListOfLegs();
		StringBuffer routeLegDetail = new StringBuffer();
		for (_Leg leg : legList) {
			routeLegDetail.append("From ");
			routeLegDetail.append(leg.getLegStartNode().getName()); 
			routeLegDetail.append(" take the ");
			routeLegDetail.append(leg.getNetworkLine().getName()); 
			routeLegDetail.append(" Line to ");
			routeLegDetail.append(leg.getLegEndNode().getName());
			routeLegDetail.append(" for ");
			routeLegDetail.append(leg.getNumberOfStops());
			if (leg.getNumberOfStops() > 1) {
				routeLegDetail.append(" stops.<br/>");
			} else {
				routeLegDetail.append(" stop.<br/>");
			}
		}
		return routeLegDetail.toString();
	}
	
	private final String getStopDetails(_Route output){
		return "Total Number of stop(s): " + (output.getNumberOfStops()+1);
	}
	private final String getTransferDetails(_Route output){
		return "Total Number of transfer(s): " + (output.getNumberOfLegs()-1); 
	}
}
