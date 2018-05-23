package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.feeds.beans.MerchandiseReport;

import java.util.List;

public interface MerchandiseService
{
    Merchandise findById(int id);

    List<Merchandise> findByProductCode(String productCode);

    List<Merchandise> findAll();

    void save(Merchandise merchandise);

    void update(Merchandise merchandise);

    MerchandiseReport getReport();
}
