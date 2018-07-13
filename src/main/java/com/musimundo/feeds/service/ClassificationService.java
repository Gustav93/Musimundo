package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Classification;
import com.musimundo.feeds.beans.ClassificationReport;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ClassificationService
{
    Classification findById(int id);

    List<Classification> findByProductCode(String productCode);

    List<Classification> findAll();

    List<Classification> findNotProcessed();

    void save(Classification classification);

    void update(Classification classification);

    ClassificationReport getReport();

	List<Classification> findClassificationByDate(Date desde, Date hasta);

	ClassificationReport getReportByCode(String code);

	ClassificationReport getReportByDate(Date fechaDesde, Date fechaHasta);

	File getCsv(List<Classification> classificationList, Filter filter);

    ClassificationReport getReport(List<Classification> classificationList, String importOrigin);

    ClassificationReport getReport(List<Classification> classificationList);
	
	void insertValues(List<Classification> clasificaciones) throws ParseException;
	
    List<ClassificationReport> getReportList(List<Classification> classificationList);

    List<String> getImportOrigin(List<Classification> classificationList);

    Company getCompany(List<Classification> classificationList);

    List<Classification> cloneClassificationList(List<Classification> classificationList);

    boolean updateStateByTypeAndImport(FeedStatus feedStatus, String errorDescription, String company);
}
