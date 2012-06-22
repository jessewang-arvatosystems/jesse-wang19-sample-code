package com.fdm.webTeller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.bank.BankAccountOutputVO;
import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;
import com.fdm.helper.ExceptionHelper;

class CreateCurrentAccountAction implements _WebDisplayAction {

	public void execute(HttpServletRequest request, HttpServletResponse response, BankGateway gateway) throws BankTellerException {
		
		String name, initialDeposit, overdraftLimit;
		BankAccountOutputVO output = null;
		
		name = request.getParameter("name");
		initialDeposit = request.getParameter("depositAmount");
		overdraftLimit = request.getParameter("overdraftAmount");
		
		ExceptionHelper.handleEmptyField(name);
		ExceptionHelper.verifyNumberField(initialDeposit);
		ExceptionHelper.verifyNumberField(overdraftLimit);

		output = gateway.createCurrentAccount(name, Double.parseDouble(initialDeposit), Double.parseDouble(overdraftLimit));
		
		request.setAttribute("account", output);
	}
	
	public RequestDispatcher setNextWebpage(HttpServletRequest request, HttpServletResponse response) {
		return request.getRequestDispatcher("accountSummary.jsp");
	}
	
}
