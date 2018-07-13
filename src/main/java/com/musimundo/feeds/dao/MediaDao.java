package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Media;
import com.musimundo.utilities.FeedStatus;

import java.util.Date;
import java.util.List;

public interface MediaDao {

    Media findById(int id);

    List<Media> findByProductCode(String productCode);

    List<Media> findAll();

    List<Media> findNotProcessed();

    void save(Media media);

    Long countAll();

    Long count(FeedStatus status);

	List<Media> findMediaByDate(Date desde, Date hasta);

	Long countAllByCode(String code);

	Long countByCode(String code, FeedStatus status);

	Long countByDate(Date fechaDesde, Date fechaHasta, FeedStatus status);

	Long countAllByDate(Date fechaDesde, Date fechaHasta);

	List<String> findFeedsByDate(Date desde, Date hasta, String empresa);

	Long countByFeed(String feedName, FeedStatus status);

	boolean insertMedialist(String insert);

	boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company);
}
