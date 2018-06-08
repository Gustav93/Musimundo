package com.musimundo.carritos.dao;

import com.musimundo.carritos.beans.StockJSON;

public interface StockCarritosDao {
	
	StockJSON getStock(String json, String url);

}
