package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Clasificacion;

import java.util.List;

public interface ClasificacionDao {

    Clasificacion findById(int id);

    List<Clasificacion> findByProductCode(String productCode);

    List<Clasificacion> findAll();

    void save(Clasificacion c);

}
