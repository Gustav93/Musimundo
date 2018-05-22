package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Stock;
import java.util.List;

public interface StockDao
{
    Stock findById(int id);

    List<Stock> findByProductCode(String productCode);

    List<Stock> findAll();

    void save(Stock stock);
}
