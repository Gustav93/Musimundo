package com.musimundo.carritos.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class StockLevel {
	String codeCARSA;
	String codeEMSA;
	String codeWarehouse;
	Integer available;
	Integer availableIE;
	String inStockStatus;	
	
	public String getInStockStatus() {
		return inStockStatus;
	}
	public void setInStockStatus(String inStockStatus) {
		this.inStockStatus = inStockStatus;
	}
	public String getCodeCARSA() {
		return codeCARSA;
	}
	public void setCodeCARSA(String codeCarsa) {
		this.codeCARSA = codeCarsa;
	}
	public String getCodeEMSA() {
		return codeEMSA;
	}
	public void setCodeEMSA(String codeEmsa) {
		this.codeEMSA = codeEmsa;
	}
	public String getCodeWarehouse() {
		return codeWarehouse;
	}
	public void setCodeWarehouse(String codeWarehouse) {
		this.codeWarehouse = codeWarehouse;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getAvailableIE() {
		return availableIE;
	}
	public void setAvailableIE(Integer availableIE) {
		this.availableIE = availableIE;
	}
	
	

}
