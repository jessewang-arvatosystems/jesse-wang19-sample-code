package com.fdm.routeplanner.model.transactions;

import java.math.BigDecimal;
import java.util.Calendar;

public interface _Transaction {

	public abstract void addTicket(_Ticket ticket);

	public abstract void setTotalPrice(BigDecimal totalPrice);

	public abstract void setTransactionID(int transactionID);

	public abstract BigDecimal getTotalPrice();
	
	public abstract void setUser_id(String userID);
	
	public abstract String getUser_id();

	public abstract void setPurchaseDate(Calendar urgh);

	public abstract int getTransactionID();
	
	public abstract void setPaymentType(String type);
	
	public abstract void setPurchaseDateNow();

	public abstract Calendar getPurchaseDate();
	
	public abstract String getPaymentType();

}
