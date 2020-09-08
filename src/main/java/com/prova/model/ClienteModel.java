package com.prova.model;

public class ClienteModel {
	
	private String originCode;
	private String cnpj;
	private String name;
	private String businessArea;
	
	public ClienteModel(String originCode, String cnpj, String name, String businessArea) {
		super();
		this.originCode = originCode;
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;
	}

	public String getOriginCode() {
		return originCode;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getName() {
		return name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	
	

}
