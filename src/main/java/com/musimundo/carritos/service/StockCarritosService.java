package com.musimundo.carritos.service;

import com.musimundo.carritos.beans.StockJSON;

public interface StockCarritosService {
	
	StockJSON getStock(String codigo, String empresa);

}
