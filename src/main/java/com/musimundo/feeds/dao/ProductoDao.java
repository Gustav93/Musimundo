package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Producto;

import java.util.List;

public interface ProductoDao
{
    Producto findById(int id);

    List<Producto> findByProductCode(String productCode);

    List<Producto> findAll();

    void save(Producto p);
}
