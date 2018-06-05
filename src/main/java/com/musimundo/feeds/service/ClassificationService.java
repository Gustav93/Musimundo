package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Classification;
import com.musimundo.feeds.beans.ClassificationReport;

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
}
