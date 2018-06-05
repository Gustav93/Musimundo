package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Media;
import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.beans.MediaReport;

import java.util.Date;
import java.util.List;

public interface MediaService
{
    Media findById(int id);

    List<Media> findByProductCode(String productCode);

    List<Media> findAll();

    List<Media> findNotProcessed();

    void save(Media media);

    void update(Media media);

    MediaReport getReport();

	MediaReport getReportByDate(Date fechaDesde, Date fechaHasta);

	MediaReport getReportByCode(String code);

	List<Media> findMediaByDate(Date desde, Date hasta);
}
