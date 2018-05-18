package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Clasificacion;

import java.util.List;

public interface ClasificationService
{
    Clasificacion findById(int id);

    List<Clasificacion> findByProductCode(String productCode);

    List<Clasificacion> findAll();

    void save(Clasificacion c);

    void update(Clasificacion c);
}
