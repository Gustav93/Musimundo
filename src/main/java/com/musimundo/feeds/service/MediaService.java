package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.*;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.Filter;

import java.io.File;
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

	File getCsv(List<Media> mediaList, Filter filter);

    MediaReport getReport(List<Media> mediaList, String importOrigin);

    List<MediaReport> getReportList(List<Media> mediaList);

    List<String> getImportOrigin(List<Media> mediaList);

    Company getCompany(List<Media> mediaList);

}
