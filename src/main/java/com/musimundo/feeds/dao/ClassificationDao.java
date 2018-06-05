package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Classification;
import com.musimundo.utilities.FeedStatus;

import java.util.Date;
import java.util.List;

public interface ClassificationDao {

    Classification findById(int id);

    List<Classification> findByProductCode(String productCode);

    List<Classification> findAll();

	List<Classification> findNotProcessed();

    void save(Classification classification);

    Long countAll();

    Long count(FeedStatus status);

	List<Classification> findClassificationByDate(Date desde, Date hasta);

	Long countAllByCode(String code);

	Long countByCode(String code, FeedStatus status);

	Long countByDate(Date fechaDesde, Date fechaHasta, FeedStatus status);

	Long countAllByDate(Date fechaDesde, Date fechaHasta);

	List<String> findFeedsByDate(Date desde, Date hasta, String empresa);

	Long countByFeed(String feedName, FeedStatus status);
}
