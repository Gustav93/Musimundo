package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Classification;
import com.musimundo.utilities.FeedStatus;

import java.util.List;

public interface ClassificationDao {

    Classification findById(int id);

    List<Classification> findByProductCode(String productCode);

    List<Classification> findAll();

    void save(Classification classification);

    Long countAll();

    Long count(FeedStatus status);
}
