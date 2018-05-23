package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Classification;
import com.musimundo.feeds.beans.ClassificationReport;
import com.musimundo.feeds.dao.ClassificationDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("classificacionService")
@Transactional
public class ClassificationServiceImpl implements ClassificationService {

    @Autowired
    ClassificationDao dao;

    @Override
    public Classification findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Classification> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Classification> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Classification classification) {
        dao.save(classification);
    }

    @Override
    public void update(Classification classification) {
        Classification entity = dao.findById(classification.getId());

        entity.setProductCode(classification.getProductCode());
        entity.setAttCode(classification.getAttCode());
        entity.setCategoryCode(classification.getCategoryCode());
        entity.setAttValue(classification.getAttValue());
        entity.setImportOrigin(classification.getImportOrigin());
        entity.setProcessingDate(classification.getProcessingDate());
        entity.setFeedStatus(classification.getFeedStatus());
        entity.setErrorDescription(classification.getErrorDescription());
        entity.setCompany(classification.getCompany());
    }

    @Override
    public ClassificationReport getReport() {
        ClassificationReport report = new ClassificationReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
}
