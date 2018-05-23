package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.beans.StockReport;

import java.util.List;

public interface StockService {

    Stock findById(int id);

    List<Stock> findByProductCode(String productCode);

    List<Stock> findAll();

    void save(Stock stock);

    void update(Stock stock);

    StockReport getReport();
}
