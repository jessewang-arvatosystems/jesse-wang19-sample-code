package com.fdm.webTeller;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;


public enum WebTeller {
	
	INSTANCE;
	
	private static final String PACKAGE_FILE = "com.fdm.webTeller.";
	
	private Map<Integer, String> listOfActions = new TreeMap<Integer, String>();
	private BankGateway gateway = BankGateway.INSTANCE;
	private WebTellerActionLoader loader = new WebTellerActionLoader();
	private RequestDispatcher view;
	
	public void setupActions() {
		loader.loadActions(listOfActions);
	}
	
	public void actionUserInput(HttpServletRequest request, HttpServletResponse response, int actionType) throws ServletException, IOException
	{	
		try {
			_WebDisplayAction action = (_WebDisplayAction) Class.forName(PACKAGE_FILE + listOfActions.get(actionType)).newInstance();
			action.execute(request, response, gateway);
			view = action.setNextWebpage(request, response);
		} catch (BankTellerException e) {
			request.setAttribute("errorMsg", e.getErrorMsg());			
			view = request.getRequestDispatcher("errorPage.jsp");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		view.forward(request, response);
	}
	
}