package com.fdm.webTeller;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.bank.BankAccountOutputVO;
import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;

public class ListAllAccountsAction implements _WebDisplayAction {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, BankGateway gateway)
			throws NumberFormatException, BankTellerException {
		
		ArrayList<BankAccountOutputVO> listOfAccounts = null;

		listOfAccounts = gateway.listAllAccounts();
		request.setAttribute("list", listOfAccounts);
	}

	public RequestDispatcher setNextWebpage(HttpServletRequest request,
			HttpServletResponse response) {

		return request.getRequestDispatcher("viewAccounts.jsp");
	}

}
