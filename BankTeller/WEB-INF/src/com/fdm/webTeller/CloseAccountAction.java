package com.fdm.webTeller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;
import com.fdm.helper.ExceptionHelper;

public class CloseAccountAction implements _WebDisplayAction {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, BankGateway gateway)
			throws NumberFormatException, BankTellerException {
	
		String id;
		
		id = request.getParameter("ID"); 
		
		ExceptionHelper.verifyNumberField(id);
		
		gateway.closeAccount(Integer.parseInt(id));
	}

	public RequestDispatcher setNextWebpage(HttpServletRequest request,
			HttpServletResponse response) {

		return request.getRequestDispatcher("closedAccount.jsp");
	}
	
}
