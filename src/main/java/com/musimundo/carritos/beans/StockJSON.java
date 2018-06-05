package com.musimundo.carritos.beans;

import java.util.List;

public class StockJSON {
	
	private List<StockLevel> stockLevels;
	
	private List<String> deliveryMethods;
	
	private List<PointOfSale> pointOfSales;

	public List<StockLevel> getStockLevels() {
		return stockLevels;
	}

	public void setStockLevels(List<StockLevel> stockLevels) {
		this.stockLevels = stockLevels;
	}

	public List<String> getDeliveryMethods() {
		return deliveryMethods;
	}

	public void setDeliveryMethods(List<String> deliveryMethods) {
		this.deliveryMethods = deliveryMethods;
	}

	public List<PointOfSale> getPointOfSales() {
		return pointOfSales;
	}

	public void setPointOfSales(List<PointOfSale> pointsOfSales) {
		this.pointOfSales = pointsOfSales;
	}
	
}
