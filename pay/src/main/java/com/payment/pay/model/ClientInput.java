package com.payment.pay.model;

import java.util.List;

public class ClientInput {
	
	private String currency;
	public List<Item> items;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

}
