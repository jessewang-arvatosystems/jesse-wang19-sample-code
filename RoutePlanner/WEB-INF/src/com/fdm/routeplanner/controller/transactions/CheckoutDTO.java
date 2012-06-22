package com.fdm.routeplanner.controller.transactions;

import java.util.List;

public class CheckoutDTO {
	
	private List<String> route;
	private String startStation;
	private String endStation;
	private String zoneNumber;
	private String userID;
	private String travelDay;
	private String travelMonth;
	private String travelYear;
	private String seniorPrice;
	private String juniorPrice;
	private String adultPrice;
	private String numOfSeniors;
	private String numOfJuniors;
	private String numOfAdults;
	private String seniorSubTotal;
	private String juniorSubTotal;
	private String adultSubTotal;
	private String paymentType;
	private String totalPrice;
	
	private CheckoutDTO(){}
	
	public static Builder createBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		CheckoutDTO checkout = new CheckoutDTO();
		private boolean done = false;
		private Builder(){}
		
		/**
		 * @param route the route to set
		 */
		public Builder setRoute(List<String> route) {
			check();
			checkout.route = route;
			return this;
		}

		/**
		 * @param startStation the startStation to set
		 */
		public Builder setStartStation(String startStation) {
			check();
			checkout.startStation = startStation;
			return this;
		}

		/**
		 * @param endStation the endStation to set
		 */
		public Builder setEndStation(String endStation) {
			check();
			checkout.endStation = endStation;
			return this;
		}

		/**
		 * @param zoneNumber the zoneNumber to set
		 */
		public Builder setZoneNumber(String zoneNumber) {
			check();
			checkout.zoneNumber = zoneNumber;
			return this;
		}

		/**
		 * @param userID the userID to set
		 */
		public Builder setUserID(String userID) {
			check();
			checkout.userID = userID;
			return this;
		}

		/**
		 * @param travelDay the travelDay to set
		 */
		public Builder setTravelDay(String travelDay) {
			check();
			checkout.travelDay = travelDay;
			return this;
		}

		/**
		 * @param travelMonth the travelMonth to set
		 */
		public Builder setTravelMonth(String travelMonth) {
			check();
			checkout.travelMonth = travelMonth;
			return this;
		}

		/**
		 * @param travelYear the travelYear to set
		 */
		public Builder setTravelYear(String travelYear) {
			check();
			checkout.travelYear = travelYear;
			return this;
		}

		/**
		 * @param seniorPrice the seniorPrice to set
		 */
		public Builder setSeniorPrice(String seniorPrice) {
			check();
			checkout.seniorPrice = seniorPrice;
			return this;
		}

		/**
		 * @param juniorPrice the juniorPrice to set
		 */
		public Builder setJuniorPrice(String juniorPrice) {
			check();
			checkout.juniorPrice = juniorPrice;
			return this;
		}

		/**
		 * @param adultPrice the adultPrice to set
		 */
		public Builder setAdultPrice(String adultPrice) {
			check();
			checkout.adultPrice = adultPrice;
			return this;
		}

		/**
		 * @param numOfSeniors the numOfSeniors to set
		 */
		public Builder setNumOfSeniors(String numOfSeniors) {
			check();
			checkout.numOfSeniors = numOfSeniors;
			return this;
		}

		/**
		 * @param numOfJuniors the numOfJuniors to set
		 */
		public Builder setNumOfJuniors(String numOfJuniors) {
			check();
			checkout.numOfJuniors = numOfJuniors;
			return this;
		}

		/**
		 * @param numOfAdults the numOfAdults to set
		 */
		public Builder setNumOfAdults(String numOfAdults) {
			check();
			checkout.numOfAdults = numOfAdults;
			return this;
		}

		/**
		 * @param seniorSubTotal the seniorSubTotal to set
		 */
		public Builder setSeniorSubTotal(String seniorSubTotal) {
			check();
			checkout.seniorSubTotal = seniorSubTotal;
			return this;
		}

		/**
		 * @param juniorSubTotal the juniorSubTotal to set
		 */
		public Builder setJuniorSubTotal(String juniorSubTotal) {
			check();
			checkout.juniorSubTotal = juniorSubTotal;
			return this;
		}

		/**
		 * @param adultSubTotal the adultSubTotal to set
		 */
		public Builder setAdultSubTotal(String adultSubTotal) {
			check();
			checkout.adultSubTotal = adultSubTotal;
			return this;
		}

		/**
		 * @param paymentType the paymentType to set
		 */
		public Builder setPaymentType(String paymentType) {
			check();
			checkout.paymentType = paymentType;
			return this;
		}

		/**
		 * @param totalPrice the totalPrice to set
		 */
		public Builder setTotalPrice(String totalPrice) {
			check();
			checkout.totalPrice = totalPrice;
			return this;
		}
		
		public CheckoutDTO build(){
			done=true;
			if (checkout.route!=null &&
					checkout.startStation!=null &&
					checkout.endStation!=null &&
					checkout.zoneNumber!=null &&
					checkout.userID!=null &&
					checkout.travelDay!=null &&
					checkout.travelMonth!=null &&
					checkout.travelYear!=null &&
					checkout.seniorPrice!=null &&
					checkout.juniorPrice!=null &&
					checkout.adultPrice!=null &&
					checkout.numOfSeniors!=null &&
					checkout.numOfJuniors!=null &&
					checkout.numOfAdults!=null &&
					checkout.seniorSubTotal!=null &&
					checkout.juniorSubTotal!=null &&
					checkout.adultSubTotal!=null &&
					//checkout.paymentType!=null &&
					checkout.totalPrice!=null)
			return checkout;
			throw new IllegalArgumentException("Not enough arguments");
		}
		private void check() {
            if (done)
            	throw new IllegalArgumentException("Do use other builder to create new instance");
		}
	}

	/**
	 * @return the route
	 */
	public List<String> getRoute() {
		return route;
	}

	/**
	 * @return the startStation
	 */
	public String getStartStation() {
		return startStation;
	}

	/**
	 * @return the endStation
	 */
	public String getEndStation() {
		return endStation;
	}

	/**
	 * @return the zoneNumber
	 */
	public String getZoneNumber() {
		return zoneNumber;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @return the travelDay
	 */
	public String getTravelDay() {
		return travelDay;
	}

	/**
	 * @return the travelMonth
	 */
	public String getTravelMonth() {
		return travelMonth;
	}

	/**
	 * @return the travelYear
	 */
	public String getTravelYear() {
		return travelYear;
	}

	/**
	 * @return the seniorPrice
	 */
	public String getSeniorPrice() {
		return seniorPrice;
	}

	/**
	 * @return the juniorPrice
	 */
	public String getJuniorPrice() {
		return juniorPrice;
	}

	/**
	 * @return the adultPrice
	 */
	public String getAdultPrice() {
		return adultPrice;
	}

	/**
	 * @return the numOfSeniors
	 */
	public String getNumOfSeniors() {
		return numOfSeniors;
	}

	/**
	 * @return the numOfJuniors
	 */
	public String getNumOfJuniors() {
		return numOfJuniors;
	}

	/**
	 * @return the numOfAdults
	 */
	public String getNumOfAdults() {
		return numOfAdults;
	}

	/**
	 * @return the seniorSubTotal
	 */
	public String getSeniorSubTotal() {
		return seniorSubTotal;
	}

	/**
	 * @return the juniorSubTotal
	 */
	public String getJuniorSubTotal() {
		return juniorSubTotal;
	}

	/**
	 * @return the adultSubTotal
	 */
	public String getAdultSubTotal() {
		return adultSubTotal;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @return the totalPrice
	 */
	public String getTotalPrice() {
		return totalPrice;
	}
}
