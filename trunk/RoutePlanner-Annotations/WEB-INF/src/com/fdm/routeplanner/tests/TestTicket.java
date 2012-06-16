package com.fdm.routeplanner.tests;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Test;
import com.fdm.routeplanner.model.transactions.Ticket;

public class TestTicket 
{

	private Ticket ticket = new Ticket();
	@Test
	public void testGetTicket_id() 
	{
		ticket.setTicketID(10);
		assertEquals(10,ticket.getTicketID());
		
	}

	@Test
	public void testGetStartStation() 
	{
		ticket.setStartStation("Camden");
		assertEquals("Camden", ticket.getStartStation());
	}

	@Test
	public void testGetEndStation() 
	{
		ticket.setStartStation("Edgeware");
		assertEquals("Edgeware", ticket.getStartStation());
	}
	
	@Test
	public void testGetPrice() 
	{
		BigDecimal price= new BigDecimal(4.35);
		ticket.setPrice(price);
		assertEquals("4.35",ticket.getPrice().toString());
	}
	
	
}
