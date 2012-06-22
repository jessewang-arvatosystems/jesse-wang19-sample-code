package com.fdm.webTeller;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.bank.BankAccountOutputVO;
import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;
import com.fdm.helper.ExceptionHelper;

public class ViewAccountAction implements _WebDisplayAction {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, BankGateway gateway)
			throws NumberFormatException, BankTellerException {
		
		ArrayList<BankAccountOutputVO> listOfAccounts = null;
		String id;
		
		id = request.getParameter("ID");
		
		ExceptionHelper.verifyNumberField(id);
		
		listOfAccounts = new ArrayList<BankAccountOutputVO>();
		listOfAccounts.add(gateway.viewAccountSummary(Integer.parseInt(id)));

		request.setAttribute("list", listOfAccounts);
	}

	public RequestDispatcher setNextWebpage(HttpServletRequest request,
			HttpServletResponse response) {

		return request.getRequestDispatcher("viewAccounts.jsp");
	}

}
