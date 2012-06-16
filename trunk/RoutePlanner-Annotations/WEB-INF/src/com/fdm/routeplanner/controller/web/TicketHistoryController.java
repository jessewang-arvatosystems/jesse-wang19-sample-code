package com.fdm.routeplanner.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.transactions.TicketDAO;
import com.fdm.routeplanner.model.transactions._Ticket;

@Controller
@Scope("request")
class TicketHistoryController extends AbstractControllerTemplate {

	@Value("$url{ticketHistoryURL}") 
	private String urlPath;
	
	@Value("#{ticketDAO}")
	private TicketDAO ticketDAO;
	
	@RequestMapping("/ticketHistory.htm")
	public ModelAndView handleRequestInternal(	@RequestParam(value = "transactionID", required = false) String transactionID,
												ModelAndView modelAndView) {
		try {
			List<_Ticket> ticketList = ticketDAO.getTicketHistory(transactionID);
			modelAndView.addObject("ticketList", ticketList);
			modelAndView.setViewName(urlPath);
		} catch (RoutePlannerException e) {
			myLogger.error("Ticket History Error for Transaction ID: " + transactionID);
			myLogger.error(e.getMessage());
			modelAndView.addObject("message", "Error in retrieving Ticket History. Please contact your administrator!");
			modelAndView.setViewName(errorURL);
		}
		
		return modelAndView;
	}
	
	@SuppressWarnings("unused")
	private final void setTicketDAO(TicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}
	
}
