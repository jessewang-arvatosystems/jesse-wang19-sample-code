package com.fdm.routeplanner.model.transactions;

import java.math.BigDecimal; 
import java.util.Calendar;

public interface _Ticket 
{
	/**
	 * @return the ticket_id
	 */
	public abstract int getTicketID();
	/**
	 * @return the startStation
	 */
	public abstract String getStartStation();
	/**
	 * @return the endStation
	 */
	public abstract String getEndStation(); 
	/**
	 * @return the date of travel
	 */
	public abstract Calendar getTravelDate();
		
	/**
	 * @return the price of the ticket
	 */
	public abstract BigDecimal getPrice();

	/**
	 * @param ticket_id the ticket_id to set
	 */
	public abstract void setTicketID(int ticketID);
	
	/**
	 * @param startStation the startStation to set
	 */
	public abstract void setStartStation(String startStation);
	/**
	 * @param endStation the endStation to set
	 */
	public abstract void setEndStation(String endStation);
	/**
	 * @param year the year of trip
	 * @param month the month of the trip
	 * @param date the date to set
	 */
	public abstract void setTravelDateEnhanced(int year, int month, int day);
	
	public abstract void setTravelDate(Calendar cal);
		/**
	 * @param prices the price of the ticket
	 */
	public abstract void setPrice(BigDecimal prices);
	
	public abstract void setTransactionID(int transID);
	public abstract int  getTransactionID();
	
	public abstract void setTicketType(String ticketType);

}
