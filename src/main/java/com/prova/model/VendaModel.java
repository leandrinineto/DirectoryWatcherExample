package com.prova.model;

import java.util.List;

public class VendaModel {
	
	private String originCode;
	private String saleId;
	private List<DetalheVendaModel> saleDetail;
	private String salesmanName;
	
	
	public VendaModel(String originCode, String saleId, List<DetalheVendaModel> saleDetail, String salesmanName) {
		this.originCode = originCode;
		this.saleId = saleId;
		this.saleDetail = saleDetail;
		this.salesmanName = salesmanName;
	}


	public String getOriginCode() {
		return originCode;
	}


	public String getSaleId() {
		return saleId;
	}


	public List<DetalheVendaModel> getSaleDetail() {
		return saleDetail;
	}


	public String getSalesmanName() {
		return salesmanName;
	}
	

	

}
