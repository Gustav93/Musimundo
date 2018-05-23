package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.utilities.FeedStatus;

import java.util.List;

public interface MerchandiseDao {

    Merchandise findById(int id);

    List<Merchandise> findByProductCode(String productCode);

    List<Merchandise> findAll();

    void save(Merchandise merchandise);

    Long countAll();

    Long count(FeedStatus status);
}
