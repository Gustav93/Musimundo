package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Price;
import com.musimundo.feeds.beans.PriceReport;
import com.musimundo.feeds.dao.PriceDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("priceService")
@Transactional
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceDao dao;

    @Override
    public Price findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Price> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Price> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Price price) {
        dao.save(price);
    }

    @Override
    public void update(Price price) {

        Price entity = dao.findById(price.getId());

        if(entity != null)
        {
            entity.setProductCode(price.getProductCode());
            entity.setCurrency(price.getCurrency());
            entity.setOnlinePrice(price.getOnlinePrice());
            entity.setStorePrice(price.getStorePrice());
            entity.setHasPriority(price.getHasPriority());
            entity.setImportOrigin(price.getImportOrigin());
            entity.setProcessingDate(price.getProcessingDate());
            entity.setFeedStatus(price.getFeedStatus());
            entity.setErrorDescription(price.getErrorDescription());
            entity.setCompany(price.getCompany());
        }
    }

    @Override
    public PriceReport getReport() {
        PriceReport report = new PriceReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
}
