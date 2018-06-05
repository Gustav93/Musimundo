package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.feeds.beans.MerchandiseReport;

import java.util.Date;
import java.util.List;

public interface MerchandiseService
{
    Merchandise findById(int id);

    List<Merchandise> findByProductCode(String productCode);

    List<Merchandise> findAll();

    List<Merchandise> findNotProcessed();

    void save(Merchandise merchandise);

    void update(Merchandise merchandise);

    MerchandiseReport getReport();

	MerchandiseReport getReportByCode(String code);

	List<Merchandise> findMerchandiseByDate(Date desde, Date hasta);

	MerchandiseReport getReportByDate(Date fechaDesde, Date fechaHasta);
}
