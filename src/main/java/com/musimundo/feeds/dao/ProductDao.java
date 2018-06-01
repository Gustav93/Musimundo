package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Product;
import com.musimundo.utilities.FeedStatus;

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
}
