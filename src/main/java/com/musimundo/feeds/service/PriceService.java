package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Precio;

import java.util.List;

public interface PriceService {

    Precio findById(int id);

    List<Precio> findByProductCode(String productCode);

    List<Precio> findAll();

    void save(Precio p);

    void update(Precio p);
}
