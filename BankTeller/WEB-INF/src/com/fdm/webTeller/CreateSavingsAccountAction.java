package com.fdm.webTeller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.bank.BankAccountOutputVO;
import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;
import com.fdm.helper.ExceptionHelper;

class CreateSavingsAccountAction implements _WebDisplayAction {

	public void execute(HttpServletRequest request, HttpServletResponse response, BankGateway gateway) throws BankTellerException {
		
		String name, initialDeposit;
		BankAccountOutputVO output = null;
		
		name = request.getParameter("name");
		initialDeposit = request.getParameter("depositAmount");
		
		ExceptionHelper.handleEmptyField(name);
		ExceptionHelper.verifyNumberField(initialDeposit);
		
		output = gateway.createSavingsAccount(name, Double.parseDouble(initialDeposit));
		
		request.setAttribute("account", output);
	}
	
	public RequestDispatcher setNextWebpage(HttpServletRequest request, HttpServletResponse response) {
		return request.getRequestDispatcher("accountSummary.jsp");
	}
	
}
