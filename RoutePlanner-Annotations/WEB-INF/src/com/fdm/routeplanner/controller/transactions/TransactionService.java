package com.fdm.routeplanner.controller.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.transactions.TransactionDTO;
import com.fdm.routeplanner.model.transactions.TransactionSystem;

@Service("transactionService")
@Scope("request")
public class TransactionService {

	private final TransactionSystem transactionSystem;
	
	@Autowired
	public TransactionService(@Value("#{transactionSystem}") TransactionSystem transactionSystem) {
		this.transactionSystem = transactionSystem;
	}
		
	public final TransactionDTO executeTransaction(CheckoutDTO checkoutVO) throws RoutePlannerException{
		TransactionDTO transactionDTO = transactionSystem.execute(checkoutVO);
		return transactionDTO;
	}
	
}
