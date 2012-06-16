package com.fdm.routeplanner.model.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("transactionBuilder")
@Scope("request")
class TransactionBuilder {
	
	private final _Transaction transaction;
	
	@Autowired
	TransactionBuilder(@Value("#{transaction}") _Transaction transaction) {
		this.transaction = transaction;
	}

	final _Transaction buildTransaction(List<_Ticket> tickets)
	{
		for(_Ticket ticket:tickets)
			transaction.addTicket(ticket);
		return transaction;
		
	}
	
}