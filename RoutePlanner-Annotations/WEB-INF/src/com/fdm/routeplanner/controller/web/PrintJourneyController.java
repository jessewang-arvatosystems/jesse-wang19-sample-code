package com.fdm.routeplanner.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model._Journey;

@Controller
@Scope("request")
class PrintJourneyController extends AbstractControllerTemplate {
	
	@Value("$url{printJourneyURL}")
	private String urlPath;
	
	@RequestMapping("/printJourney.htm")
	public ModelAndView handleRequestInternal(	@RequestParam(value = "startStation", required = false) 	String startStation,
												@RequestParam(value = "endStation", required = false)		String endStation,
												HttpSession session,
												ModelAndView modelAndView) {
		_Journey journey;

		try {
			journey = routeController.getRoute(startStation, endStation);
			session.setAttribute("persistentStart", startStation);
			session.setAttribute("persistentEnd", endStation);
			modelAndView.addObject("resultList", journey);
			modelAndView.setViewName(urlPath);
		} catch (RoutePlannerException e) {
			myLogger.error("getRoute for start st. " + startStation + " and end station " + endStation);
			myLogger.error(e.getMessage());
			modelAndView.addObject("message", "Error in the RoutePlanner. Please contact your administrator!");
			modelAndView.setViewName(errorURL);
		}
		
		return modelAndView;
	}

}