package com.fdm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.webTeller.WebTeller;

public final class WebTellerServlet extends HttpServlet {

	private static final long serialVersionUID = -651445828028992845L;
	private WebTeller webTeller;
	
	public void init() {
		webTeller = WebTeller.INSTANCE;
		webTeller.setupActions();
	}
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionType;
		
		actionType = request.getParameter("ActionType");
		webTeller.actionUserInput(request, response, Integer.parseInt(actionType));
	}

}