package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Producto;

import java.util.List;

public interface ProductService
{
    Producto findById(int id);

    List<Producto> findByProductCode(String productCode);

    List<Producto> findAll();

    void save(Producto p);

    void update(Producto p);
}
