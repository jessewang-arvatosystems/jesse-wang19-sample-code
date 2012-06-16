package com.fdm.routeplanner.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdm.routeplanner.exception.RoutePlannerException;

@Controller
@Scope("request")
class LoadStationsController extends AbstractControllerTemplate {
	
	@SuppressWarnings("unused")
	@RequestMapping("/buyTickets.htm")
	private final ModelAndView buyTickets(@Value("$url{buyTicketsURL}") String url, ModelAndView modelAndView) {
		setUrlPath(url);
		return handleRequestInternal(modelAndView);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/searchRoutes.htm")
	private final ModelAndView searchRoutes(@Value("$url{searchRoutesURL}") String url, ModelAndView modelAndView) {
		setUrlPath(url);
		return handleRequestInternal(modelAndView);
	}
	
	public ModelAndView handleRequestInternal(ModelAndView modelAndView) {	
		String[] stations;
		try {			
			stations = routeController.getStationNames();
			modelAndView.addObject("stationList", stations);
			modelAndView.setViewName(urlPath);
		} catch (RoutePlannerException e) {
			myLogger.error(e.getMessage());
			modelAndView.addObject("message", "Error in finding available stations. Please contact your administrator!");
			modelAndView.setViewName(errorURL);
		}
		return modelAndView;
	}
	
}