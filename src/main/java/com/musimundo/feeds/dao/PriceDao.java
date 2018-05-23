package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Price;
import com.musimundo.utilities.FeedStatus;

import java.util.List;

public interface PriceDao
{
    Price findById(int id);

    List<Price> findByProductCode(String productCode);

    List<Price> findAll();

    void save(Price price);

    Long countAll();

    Long count(FeedStatus status);
}
