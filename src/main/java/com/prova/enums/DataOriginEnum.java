package com.prova.enums;

import java.util.HashMap;
import java.util.Map;

public enum DataOriginEnum {
	
	VENDEDOR("001"),
	CLIENTE("002"),
	VENDA("003");
	
	private String originCode;
	
	private static final Map<String, DataOriginEnum> LOOKUP = new HashMap<String, DataOriginEnum>();
	
	static {
		for (DataOriginEnum e : DataOriginEnum.values()) {
			LOOKUP.put(e.getOriginCode(), e);
		}
	}
	
	private DataOriginEnum(String originCode) {
		this.originCode = originCode;
	}
	
	public String getOriginCode() {
		return originCode;
	}
	
	public static DataOriginEnum originValue(String value) {
		return value != null ? LOOKUP.get(value) : null;
	}

}
