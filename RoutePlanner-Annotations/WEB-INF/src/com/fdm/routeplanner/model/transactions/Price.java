package com.fdm.routeplanner.model.transactions;

import java.math.BigDecimal;

class Price {

	private int zone;
	private BigDecimal senior;
	private BigDecimal adult;
	private BigDecimal junior;
	
	/**
	 * @return the zone
	 */
	public int getZone() {
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(int zone) {
		this.zone = zone;
	}
	/**
	 * @return the senior
	 */
	public BigDecimal getSenior() {
		return senior;
	}
	/**
	 * @param senior the senior to set
	 */
	public void setSenior(BigDecimal senior) {
		this.senior = senior;
	}
	/**
	 * @return the adult
	 */
	public BigDecimal getAdult() {
		return adult;
	}
	/**
	 * @param adult the adult to set
	 */
	public void setAdult(BigDecimal adult) {
		this.adult = adult;
	}
	/**
	 * @return the junior
	 */
	public BigDecimal getJunior() {
		return junior;
	}
	/**
	 * @param junior the junior to set
	 */
	public void setJunior(BigDecimal junior) {
		this.junior = junior;
	}

}
