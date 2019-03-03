package com.afkl.cases.df.model;

import java.io.Serializable;

public class FareVO implements Serializable{

	private static final long serialVersionUID = 2889979706608404628L;
	
	private String amount;
	private String currency;
	private String origin;
	private String destination;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
