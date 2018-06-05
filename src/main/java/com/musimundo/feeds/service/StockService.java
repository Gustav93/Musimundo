package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.beans.StockReport;

import java.util.Date;
import java.util.List;

public interface StockService {

    Stock findById(int id);

    List<Stock> findByProductCode(String productCode);

    List<Stock> findAll();

    List<Stock> findNotProcessed();

    void save(Stock stock);

    void update(Stock stock);

    StockReport getReport();

	List<Stock> findStockByDate(Date desde, Date hasta);

	StockReport getReportByCode(String code);

	StockReport getReportByDate(Date fechaDesde, Date fechaHasta);
}
