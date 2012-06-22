package com.fdm.routeplanner.controller.web;
import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdm.routeplanner.controller.transactions.CheckoutDTO;
import com.fdm.routeplanner.controller.transactions.TransactionService;
import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model._Journey;
import com.fdm.routeplanner.model.transactions.TransactionDTO;

@Controller
@Scope("request")
class BuyTicketsController extends AbstractControllerTemplate {
	
	@Value("$url{buyAndBuildTicketsURL}")
	private String urlPath;
	
	@Value("#{transactionService}")
	private TransactionService transactionService;
	
	@RequestMapping("/buyAndBuildTickets.htm")
	public ModelAndView handleRequestInternal( 	@RequestParam(value = "userid", required = false) 		String userID,
												@RequestParam(value = "startStation", required = false) 	String startStation,
												@RequestParam(value = "endStation", required = false)		String endStation,
												@RequestParam(value = "day_id", required = false)			String travelDay,
												@RequestParam(value = "month_id", required = false)		String travelMonth,
												@RequestParam(value = "year_id", required = false)		String travelYear,
												@RequestParam(value = "adultPrice", required = false)		String adultPrice,
												@RequestParam(value = "seniorPrice", required = false)	String seniorPrice,
												@RequestParam(value = "juniorPrice", required = false)	String juniorPrice,
												@RequestParam(value = "numberSeniors", required = false)	String numOfSeniors,
												@RequestParam(value = "numberJuniors", required = false)	String numOfJuniors,
												@RequestParam(value = "numberAdults", required = false)	String numOfAdults,
												@RequestParam(value = "paymentType", required = false)	String paymentType,
												@RequestParam(value = "totalPrice", required = false)		String totalPrice,
												ModelAndView modelAndView) {	
		_Journey journey;
		
		try {
			journey = routeController.getOneRoute(startStation, endStation);
			int zone = journey.getLowestZone();
			String stringZone = Integer.toString(zone);
			String adultSubTotal = this.multiplyStrings(numOfAdults, adultPrice);
			String juniorSubTotal = this.multiplyStrings(numOfJuniors, juniorPrice);
			String seniorSubTotal = this.multiplyStrings(numOfSeniors, seniorPrice);
			try {
				CheckoutDTO checkout = CheckoutDTO.createBuilder()
													.setRoute(journey.getMessage())
													.setStartStation(startStation)
													.setEndStation(endStation)
													.setZoneNumber(stringZone)
													.setUserID(userID)
													.setTravelDay(travelDay)
													.setTravelMonth(travelMonth)
													.setTravelYear(travelYear)
													.setSeniorPrice(seniorPrice)
													.setJuniorPrice(juniorPrice)
													.setAdultPrice(adultPrice)
													.setNumOfSeniors(numOfSeniors)
													.setNumOfJuniors(numOfJuniors)
													.setNumOfAdults(numOfAdults)
													.setPaymentType(paymentType)
													.setSeniorSubTotal(seniorSubTotal)
													.setJuniorSubTotal(juniorSubTotal)
													.setAdultSubTotal(adultSubTotal)
													.setTotalPrice(totalPrice).build();
				TransactionDTO successBuyVO = transactionService.executeTransaction(checkout);
				modelAndView.addObject("successBuyVO", successBuyVO);
				modelAndView.setViewName(urlPath);
			} catch(IllegalArgumentException e){
				throw new RoutePlannerException(e);
			}
		} catch(RoutePlannerException e){
			myLogger.error("Buy Ticket Error for User ID: " + userID);
			myLogger.error(e.getMessage());
			modelAndView.addObject("message", "Error in buying Ticket. Please contact your administrator!");
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
	 * @param transactionService the transactionController to set
	 */
	@SuppressWarnings("unused")
	private final void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

}