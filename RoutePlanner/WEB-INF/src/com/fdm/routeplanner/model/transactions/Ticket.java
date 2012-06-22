package com.fdm.routeplanner.model.transactions;

import java.math.BigDecimal; 
import java.math.MathContext;
import java.util.Calendar;

public class Ticket implements _Ticket
{
	private int ticketID;
	private int transactionID;
	private String startStation;
	private String endStation;
	private Calendar travelDate;
	private String ticketType;
	private BigDecimal price; 
	
	/**
	 * @param ticketID
	 * @param userID
	 * @param startStation
	 * @param endStation
	 * @param travelDate
	 * @param person
	 * @param price
	 */
	public Ticket(String userID, String startStation,
			String endStation, Calendar travelDate, String ticketTYPE,
			BigDecimal price) {
		this.startStation = startStation;
		this.endStation = endStation;
		this.travelDate = travelDate;
		this.ticketType = ticketTYPE;
		this.price = price;
	}

	public Ticket(){}
	
	public int getTicketID() 
	{
		return ticketID;
	}
	
	public String getTicketIDString(){
		return Integer.toString(ticketID);
	}

	public String getStartStation()
	{
		return startStation;
	}

	public String getEndStation() 
	{
		return endStation;
	}

	public Calendar getTravelDate()
	{
		return travelDate;
	}
	
	public BigDecimal getPrice() 
	{ 
	      return price.setScale(2);
	} 
	
	public String getTicketType()
	{
		return ticketType;
	}
	
	public void setTicketType(String ticketTYPE){
		this.ticketType = ticketTYPE;
	}
	
	public void setTicketID(int ticketID) 
	{
		this.ticketID = ticketID;
	}

	public void setStartStation(String startStation) 
	{
		this.startStation = startStation;
	}

	public void setEndStation(String endStation)
	{
		this.endStation = endStation;
	}

	/**
	 * @param travelDate the travelDate to set
	 */
	public void setTravelDateEnhanced(int year, int month, int day) {
		this.travelDate.set(year,month,day);
	}
	
	public void setTravelDate(Calendar cal){
		this.travelDate = cal;
	}


	public void setPrice( BigDecimal price ) 
	{ 
	     this.price = price.round(new MathContext(8)).setScale(2); 
	}
	
	public void setPerson(String ticketTYPE)
	{
		this.ticketType=ticketTYPE;
	}


	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}


	public int getTransactionID() {
		return transactionID;
	}
}
