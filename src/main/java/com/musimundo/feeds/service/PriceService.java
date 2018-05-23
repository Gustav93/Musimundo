package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Price;
import com.musimundo.feeds.beans.PriceReport;

import java.util.List;

public interface PriceService {

    Price findById(int id);

    List<Price> findByProductCode(String productCode);

    List<Price> findAll();

    void save(Price price);

    void update(Price price);

    PriceReport getReport();
}
