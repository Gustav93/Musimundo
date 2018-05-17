package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Precio;

import java.util.List;

public interface PrecioDao
{
    Precio findById(int id);

    List<Precio> findByProductCode(String productCode);

    List<Precio> findAll();

    void save(Precio p);
}
