package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.feeds.beans.MerchandiseReport;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;

import java.io.File;
import java.text.ParseException;
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

	File getCsv(List<Merchandise> merchandiseList, Filter filter);

	void insertValues(List<Merchandise> merchandises) throws ParseException;
	
	MerchandiseReport getReport(List<Merchandise> merchandiseList);

    MerchandiseReport getReport(List<Merchandise> merchandiseList, String importOrigin);

    List<MerchandiseReport> getReportList(List<Merchandise> merchandiseList);

    List<String> getImportOrigin(List<Merchandise> merchandiseList);

    Company getCompany(List<Merchandise> merchandiseList);

    List<Merchandise> cloneMerchandiseList(List<Merchandise> merchandiseList);

    boolean updateStateByTypeAndImport(FeedStatus feedStatus, String errorDescription, String company);
}
