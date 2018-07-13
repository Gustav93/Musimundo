package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.beans.StockReport;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;

import java.io.File;
import java.text.ParseException;
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

	File getCsv(List<Stock> stockList, Filter filter);

    StockReport getReport(List<Stock> stockList);

    StockReport getReport(List<Stock> stockList, String importOrigin);

    List<StockReport> getReportList(List<Stock> stockList);
	
	void insertValues(List<Stock> stocks) throws ParseException;

    List<String> getImportOrigin(List<Stock> stockList);

    Company getCompany(List<Stock> stockList);

    List<Stock> cloneStockList(List<Stock> stockList);

    boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company);
}
