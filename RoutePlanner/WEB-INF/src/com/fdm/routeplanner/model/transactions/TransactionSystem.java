package com.fdm.routeplanner.model.transactions;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.controller.transactions.CheckoutDTO;
import com.fdm.routeplanner.exception.RoutePlannerException;

@Service("transactionSystem")
@Scope("request")
public final class TransactionSystem {

	
	private final TicketBuilder ticketBuilder;
	private final TransactionBuilder transactionBuilder;
	private final TransactionDAO transactionDAO;
	private final TicketDAO ticketDAO;
	private final TransactionDTO successBuyVO;
	private final Lock lock;
	
	@Autowired
	public TransactionSystem(	@Value("#{ticketBuilder}") TicketBuilder ticketBuilder,
								@Value("#{transactionBuilder}") TransactionBuilder transactionBuilder,
								@Value("#{transactionDAO}") TransactionDAO transactionDAO, 
								@Value("#{ticketDAO}") TicketDAO ticketDAO,
								@Value("#{successBuyVO}") TransactionDTO successBuyVO, 
								@Value("#{lock}")Lock lock) {
		this.ticketBuilder = ticketBuilder;
		this.transactionBuilder = transactionBuilder;
		this.transactionDAO = transactionDAO;
		this.ticketDAO = ticketDAO;
		this.successBuyVO = successBuyVO;
		this.lock = lock;
	}

	public final TransactionDTO execute(CheckoutDTO checkoutVO) throws RoutePlannerException {
		lock.lock();
		List<_Ticket> tickets = ticketBuilder.buildTickets(checkoutVO);
		_Transaction transaction=transactionBuilder.buildTransaction(tickets);
		transaction.setPurchaseDateNow();
		transaction.setUser_id(checkoutVO.getUserID());
		transaction.setTotalPrice(new BigDecimal(checkoutVO.getTotalPrice()));
		transaction.setPaymentType(checkoutVO.getPaymentType());
		transactionDAO.createTransaction(transaction);
		int transactionID = transaction.getTransactionID();
		successBuyVO.resetSuccessBuyVO();
		for (_Ticket tick : tickets){
			tick.setTransactionID(transactionID);
			ticketDAO.createTicket(tick);
			successBuyVO.addOneTicket(tick);
		}
		successBuyVO.setTotalTickets(tickets.size());
		successBuyVO.setTransactionID(transactionID);
		lock.unlock();
		return successBuyVO;
	}
	
}
