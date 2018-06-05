package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Product;
import com.musimundo.utilities.FeedStatus;

import java.util.Date;
import java.util.List;

public interface ProductDao
{
    Product findById(int id);

    List<Product> findByProductCode(String productCode);

    List<Product> findAll();

    List<Product> findNotProcessed();

    void save(Product product);

    Long countAll();

    Long count(FeedStatus status);

	Long countAllByDate(Date fechaDesde, Date fechaHasta);

	Long countByDate(Date fechaDesde, Date fechaHasta, FeedStatus status);

	Long countByCode(String Code, FeedStatus status);

	Long countAllByCode(String code);

	List<Product> findProductByDate(Date desde, Date hasta);

	List<String> findFeedsByDate(Date desde, Date hasta, String empresa);

	Long countByFeed(String feedName, FeedStatus status);
}
