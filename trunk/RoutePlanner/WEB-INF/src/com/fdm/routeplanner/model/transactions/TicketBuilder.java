package com.fdm.routeplanner.model.transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.controller.transactions.CheckoutDTO;

@Service("ticketBuilder")
@Scope("request")
class TicketBuilder 
{
	final List<_Ticket> buildTickets(CheckoutDTO checkoutVO){
		int adults = Integer.parseInt(checkoutVO.getNumOfAdults());
		int juniors = Integer.parseInt(checkoutVO.getNumOfJuniors());
		int seniors = Integer.parseInt(checkoutVO.getNumOfSeniors());
		List<_Ticket> tickets = new ArrayList<_Ticket>();
		Calendar cal = new GregorianCalendar();
		cal.set(Integer.parseInt(checkoutVO.getTravelYear()), Integer.parseInt(checkoutVO.getTravelMonth())-1, Integer.parseInt(checkoutVO.getTravelDay()), 0, 0, 0);
		for (int adult=0; adult<adults; adult++){
			tickets.add(new Ticket(checkoutVO.getUserID(),checkoutVO.getStartStation(),
			checkoutVO.getEndStation(),cal,"adult",
					new BigDecimal(checkoutVO.getAdultPrice())));
		}
		for (int junior=0; junior<juniors; junior++){
			tickets.add(new Ticket(checkoutVO.getUserID(),checkoutVO.getStartStation(),
					checkoutVO.getEndStation(),cal,"junior",
					new BigDecimal(checkoutVO.getJuniorPrice())));
		}
		for (int senior=0; senior<seniors; senior++){
			tickets.add(new Ticket(checkoutVO.getUserID(),checkoutVO.getStartStation(),
					checkoutVO.getEndStation(),cal,"senior",
					new BigDecimal(checkoutVO.getSeniorPrice())));
		}
		return tickets;
	}
}
