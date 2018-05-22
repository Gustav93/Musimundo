package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Classification;

import java.util.List;

public interface ClassificationDao {

    Classification findById(int id);

    List<Classification> findByProductCode(String productCode);

    List<Classification> findAll();

    void save(Classification classification);

}
