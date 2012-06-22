package com.fdm.webTeller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.bank.BankGateway;
import com.fdm.exceptions.BankTellerException;

public interface _WebDisplayAction {

	public void execute(HttpServletRequest request, HttpServletResponse response, BankGateway gateway) throws NumberFormatException, BankTellerException;
	public RequestDispatcher setNextWebpage(HttpServletRequest request, HttpServletResponse response);
}
