package com.fdm.routeplanner.model.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("successBuyVO")
@Scope("request")
public class TransactionDTO {
	
	@Value("#{arrayList}")
	private List<_Ticket> tickets;
	
	private int totalTickets;
	private int transactionID;
	
	public TransactionDTO(){}
	
	public final void resetSuccessBuyVO(){
		this.tickets.clear();
	}
	
	public final void setTotalTickets(int count){
		this.totalTickets = count;
	}
	
	public int getTotalTickets(){
		return totalTickets;
	}
	
	public final void addOneTicket(_Ticket ticket){
		this.tickets.add(ticket);
	}
	
	public void setTransactionID(int transactionID){
		this.transactionID = transactionID;
	}
	
	public int getTransactionID(){
		return transactionID;
	}
	
	@SuppressWarnings("unused")
	private void setTickets(List<_Ticket> setter){
		this.tickets = setter;
	}
	
	public List<_Ticket> getTickets(){
		return tickets;
	}
	
}
