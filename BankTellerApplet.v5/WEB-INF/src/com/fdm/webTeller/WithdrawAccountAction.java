package com.fdm.webTeller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.bank.BankAccountOutputVO;
import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;
import com.fdm.helper.ExceptionHelper;

public class WithdrawAccountAction implements _WebDisplayAction  { 

	public void execute(HttpServletRequest request,
			HttpServletResponse response, BankGateway gateway)
			throws NumberFormatException, BankTellerException {
		
		String amount, id;
		BankAccountOutputVO output;
		
		amount = request.getParameter("amount");
		id = request.getParameter("ID");
		
		ExceptionHelper.verifyNumberField(amount);
		ExceptionHelper.verifyNumberField(id);
		
		output = gateway.makeWithdrawal(Double.parseDouble(amount), Integer.parseInt(id));
		request.setAttribute("account", output);
	}

	public RequestDispatcher setNextWebpage(HttpServletRequest request,
			HttpServletResponse response) {
		
		return request.getRequestDispatcher("accountSummary.jsp");
	}

}