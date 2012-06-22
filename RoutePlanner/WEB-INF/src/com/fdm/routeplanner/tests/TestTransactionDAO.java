package com.fdm.routeplanner.tests;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.transactions.TransactionDAO;
import com.fdm.routeplanner.model.transactions._Transaction;

public class TestTransactionDAO 
{
	private TransactionDAO transactionDAO;
	private ApplicationContext context;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		transactionDAO = (TransactionDAO) context.getBean("transactionDAO");
	}
	
	@Test
	public void testRetrieveTransaction() {
		_Transaction transaction  = null;
		try {
			transaction = transactionDAO.retrieveTransaction(1);
		} catch (RoutePlannerException e) {
			e.printStackTrace();
		}
		
		System.out.println(transaction);
	}
	
	@Test
	public void testRetrieveHighestTransactionID() {
		int highestTransactionID = 0;

		try {
			highestTransactionID = transactionDAO.retrieveHighestTransactionID();
		} catch (RoutePlannerException e) {
			e.printStackTrace();
		}
		
		System.out.println("Highest Transaction ID is: " + highestTransactionID);
	}
	
}
