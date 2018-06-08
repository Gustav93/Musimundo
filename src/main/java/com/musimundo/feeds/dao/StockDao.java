package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.utilities.FeedStatus;

import java.util.Date;
import java.util.List;

public interface StockDao
{
    Stock findById(int id);

    List<Stock> findByProductCode(String productCode);

    List<Stock> findAll();

    List<Stock> findNotProcessed();

    void save(Stock stock);

    Long countAll();

    Long count(FeedStatus status);

	List<Stock> findStockByDate(Date desde, Date hasta);

	Long countAllByCode(String code);

	Long countByCode(String code, FeedStatus status);

	Long countByDate(Date fechaDesde, Date fechaHasta, FeedStatus status);

	Long countAllByDate(Date fechaDesde, Date fechaHasta);

	Long countByFeed(String feedName, FeedStatus status);

	List<String> findFeedsByDate(Date desde, Date hasta, String empresa);

	void saveList(List<Stock> stockList);
}
