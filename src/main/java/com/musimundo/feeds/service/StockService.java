package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Stock;

import java.util.List;

public interface StockService {

    Stock findById(int id);

    List<Stock> findByProductCode(String productCode);

    List<Stock> findAll();

    void save(Stock s);

    void update(Stock s);
}
