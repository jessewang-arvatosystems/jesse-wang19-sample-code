package com.fdm.routeplanner.model.transactions;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("transaction")
@Scope("request")
class Transaction implements _Transaction
{

	private int transactionID;
	private String paymentType;
	private Calendar purchaseDate;
	private String user_id;
	private BigDecimal totalPrice=new BigDecimal(0);
	private Queue< _Ticket> tickets= new LinkedList<_Ticket>();
	
	public Transaction(){
		
	}
	
	public int getTransactionID()
	{
		return transactionID;
	}
	
	public void setUser_id(String userID){
		this.user_id = userID;
	}
	
	public String getUser_id(){
		return user_id;
	}
		
	public void setPurchaseDateNow()
	{
		Calendar now = Calendar.getInstance();
		this.purchaseDate = now;
	}
	
	public void setPurchaseDate(Calendar date){
		this.purchaseDate = date;
		
	}
	
	public BigDecimal getTotalPrice()
	{
		return totalPrice.setScale(2);
	}
	

	public void setTransactionID(int transactionID)
	{
		this.transactionID=transactionID;
	}
	
	public void setTotalPrice(BigDecimal totalPrice)
	{
		this.totalPrice=totalPrice;
	}
	
	public void addTicket(_Ticket ticket)
	{
			tickets.add(ticket);
			totalPrice.add(ticket.getPrice());
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	@Override
	public Calendar getPurchaseDate() {
		return purchaseDate;
	}

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", paymentType="
				+ paymentType + ", purchaseDate=" + purchaseDate + ", user_id="
				+ user_id + ", totalPrice=" + totalPrice + ", tickets="
				+ tickets + "]";
	}
}
