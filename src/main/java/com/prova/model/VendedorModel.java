package com.prova.model;

import java.math.BigDecimal;

public class VendedorModel {
	
	private String originCode;
	private String cpf;
	private String name;
	private BigDecimal salary;
	
	
	public VendedorModel(String originCode, String cpf, String name, BigDecimal salary) {
		this.originCode = originCode;
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}


	public String getOriginCode() {
		return originCode;
	}

	public String getCpf() {
		return cpf;
	}

	public String getName() {
		return name;
	}


	public BigDecimal getSalary() {
		return salary;
	}
	
	
	

}
