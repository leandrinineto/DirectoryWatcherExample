package com.prova.model;

import java.math.BigDecimal;

public class DetalheVendaModel {
	
	private String item;
	private BigDecimal quantity;
	private BigDecimal price;
	
	
	public DetalheVendaModel(String item, BigDecimal quantity, BigDecimal price) {
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}

	public String getItem() {
		return item;
	}


	public BigDecimal getQuantity() {
		return quantity;
	}


	public BigDecimal getPrice() {
		return price;
	}
	
	
}
