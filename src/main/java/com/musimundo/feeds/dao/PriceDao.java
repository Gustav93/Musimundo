package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Price;
import com.musimundo.utilities.FeedStatus;

import java.util.Date;
import java.util.List;

public interface PriceDao
{
    Price findById(int id);

    List<Price> findByProductCode(String productCode);

    List<Price> findAll();

    List<Price> findNotProcessed();

    void save(Price price);

    Long countAll();

    Long count(FeedStatus status);

	List<Price> findPriceByDate(Date desde, Date hasta);

	Long countAllByCode(String code);

	Long countByCode(String code, FeedStatus status);

	Long countByDate(Date fechaDesde, Date fechaHasta, FeedStatus status);

	Long countAllByDate(Date fechaDesde, Date fechaHasta);

	List<String> findFeedsByDate(Date desde, Date hasta, String empresa);

	Long countByFeed(String feedName, FeedStatus status);

	boolean insertPricelist(String insert);

	boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company, String notOk);
}
