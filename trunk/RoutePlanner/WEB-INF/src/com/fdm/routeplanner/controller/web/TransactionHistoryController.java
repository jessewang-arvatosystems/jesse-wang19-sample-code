package com.fdm.routeplanner.controller.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.transactions.TransactionDAO;
import com.fdm.routeplanner.model.transactions._Transaction;

@Controller
@Scope("request")
class TransactionHistoryController extends AbstractControllerTemplate {
	
	@Value("$url{transactionHistoryURL}")
	private String urlPath;
	
	@Value("#{transactionDAO}")
	private TransactionDAO transactionDAO;

	@RequestMapping("/transactionHistory.htm")
	public ModelAndView handleRequestInternal(	HttpSession session,
												ModelAndView modelAndView) {
		String userID;
		
		userID = (String) session.getAttribute("userID");
		try {
			List<_Transaction> transactionList = transactionDAO.getTransactionHistory(userID);
			modelAndView.addObject("transactionList", transactionList);
			modelAndView.setViewName(urlPath);
		} catch (RoutePlannerException e) {
			myLogger.error("Transaction History Error for User ID: " + userID);
			myLogger.error(e.getMessage());
			modelAndView.addObject("message", "Error in retrieving Transaction History. Please contact your administrator!");
			modelAndView.setViewName(errorURL);
		}
		
		return modelAndView;
	}

	@SuppressWarnings("unused")
	private final void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

}
