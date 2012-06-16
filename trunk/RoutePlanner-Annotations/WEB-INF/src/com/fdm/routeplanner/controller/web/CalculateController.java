package com.fdm.routeplanner.controller.web;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdm.routeplanner.controller.transactions.CheckoutDTO;
import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model._Journey;
import com.fdm.routeplanner.model.transactions.PriceDAO;

@Controller
@Scope("request")
class CalculateController extends AbstractControllerTemplate {
	
	@Value("$url{showTicketURL}")
	private String urlPath;
	
	@Value("#{priceDAO}")
	private PriceDAO priceDAO;

	@RequestMapping("/showTicket.htm")
	public ModelAndView handleRequestInternal(	
												@RequestParam(value = "startStationBuy", required = false) 	String start,
												@RequestParam(value = "endStationBuy", required = false)	String end,
												@RequestParam(value = "day_id", required = false)			String travelDay,
												@RequestParam(value = "month_id", required = false)			String month,
												@RequestParam(value = "year_id", required = false)			String travelYear,
												@RequestParam(value = "numberAdults", required = false)		String numberAdults,
												@RequestParam(value = "numberJuniors", required = false)	String numberJuniors,
												@RequestParam(value = "numberSeniors", required = false)	String numberSeniors,
												HttpSession session,
												ModelAndView modelAndView) {	
		String travelMonth ;		
		try{
			_Journey journey = routeController.getOneRoute(start, end);
			List<String> route = journey.getMessage();
			int lowestZone = journey.getLowestZone();
			
			travelMonth = Integer.toString(Integer.parseInt(month)+1);

			String adultPrice = priceDAO.retrieveAdultPrice(lowestZone).toString();
			String juniorPrice = priceDAO.retrieveJuniorPrice(lowestZone).toString();
			String seniorPrice = priceDAO.retrieveSeniorPrice(lowestZone).toString();

			String userID = (String) session.getAttribute("userID");

			String adultSubTotal = this.multiplyStrings(numberAdults, adultPrice);
			String juniorSubTotal = this.multiplyStrings(numberJuniors, juniorPrice);
			String seniorSubTotal = this.multiplyStrings(numberSeniors, seniorPrice);

			BigDecimal totalPriceDeci = new BigDecimal(adultSubTotal)
					.add(new BigDecimal(juniorSubTotal)) 
					.add(new BigDecimal(seniorSubTotal));

			String totalPrice = totalPriceDeci.toString();
			try{
				CheckoutDTO checkout = CheckoutDTO.createBuilder().setRoute(route)
															.setStartStation(start)
															.setEndStation(end)
															.setZoneNumber(Integer.toString(lowestZone))
															.setUserID(userID)
															.setTravelDay(travelDay)
															.setTravelMonth(travelMonth)
															.setTravelYear(travelYear)
															.setSeniorPrice(seniorPrice)
															.setJuniorPrice(juniorPrice)
															.setAdultPrice(adultPrice)
															.setNumOfSeniors(numberSeniors)
															.setNumOfJuniors(numberJuniors)
															.setNumOfAdults(numberAdults)
															.setPaymentType(null)
															.setSeniorSubTotal(seniorSubTotal)
															.setJuniorSubTotal(juniorSubTotal)
															.setAdultSubTotal(adultSubTotal)
															.setTotalPrice(totalPrice).build();
				modelAndView.addObject("checkout", checkout);
				modelAndView.setViewName(urlPath);
			}catch(IllegalArgumentException e){
				throw new RoutePlannerException(e);
			}
		}catch(RoutePlannerException e){
			myLogger.error(e.getMessage());
			modelAndView.addObject("message", "Error in calculating Ticket information. Please contact your administrator!");
			modelAndView.setViewName(errorURL);
		}
		
		return modelAndView;
	}

	private final String multiplyStrings(String tickets, String price){
		BigDecimal priceDeci = new BigDecimal(price);
		BigDecimal subTotal = new BigDecimal(tickets).multiply(priceDeci);
		return subTotal.round(new MathContext(8)).setScale(2).toString();
	}

	/**
	 * @param priceDAO the priceTableDAO to set
	 */
	@SuppressWarnings("unused")
	private final void setPriceDAO(PriceDAO ptDAO){
		this.priceDAO = ptDAO;
	}

}