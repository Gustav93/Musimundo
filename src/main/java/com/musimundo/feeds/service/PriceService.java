package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Price;
import com.musimundo.feeds.beans.PriceReport;
import com.musimundo.utilities.Filter;

import java.io.File;
import java.util.Date;
import java.util.List;

public interface PriceService {

    Price findById(int id);

    List<Price> findByProductCode(String productCode);

    List<Price> findAll();

    List<Price> findNotProcessed();

    void save(Price price);

    void update(Price price);

    PriceReport getReport();

	PriceReport getReportByDate(Date fechaDesde, Date fechaHasta);

	PriceReport getReportByCode(String code);

	List<Price> findPriceByDate(Date desde, Date hasta);

	File getCsv(List<Price> priceList, Filter filter);
}
