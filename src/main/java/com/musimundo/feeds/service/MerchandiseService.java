package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Merchandise;

import java.util.List;

public interface MerchandiseService
{
    Merchandise findById(int id);

    List<Merchandise> findByProductCode(String productCode);

    List<Merchandise> findAll();

    void save(Merchandise m);

    void update(Merchandise m);
}
