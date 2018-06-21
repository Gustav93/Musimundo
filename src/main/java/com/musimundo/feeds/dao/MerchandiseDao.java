package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.utilities.FeedStatus;

import java.util.Date;
import java.util.List;

public interface MerchandiseDao {

    Merchandise findById(int id);

    List<Merchandise> findByProductCode(String productCode);

    List<Merchandise> findAll();

    List<Merchandise> findNotProcessed();

    void save(Merchandise merchandise);

    Long countAll();

    Long count(FeedStatus status);

	Long countAllByDate(Date fechaDesde, Date fechaHasta);

	Long countByDate(Date fechaDesde, Date fechaHasta, FeedStatus status);

	Long countByCode(String code, FeedStatus status);

	Long countAllByCode(String code);

	List<Merchandise> findMerchandiseByDate(Date desde, Date hasta);

	List<String> findFeedsByDate(Date desde, Date hasta, String empresa);

	Long countByFeed(String feedName, FeedStatus status);

	boolean insertMerchandiselist(String insert);

	boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company, String notOk);
}
