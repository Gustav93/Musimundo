package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.beans.StockReport;
import com.musimundo.feeds.dao.StockDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("stockService")
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    StockDao dao;

    @Override
    public Stock findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Stock> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Stock> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Stock stock) {
        dao.save(stock);
    }

    @Override
    public void update(Stock stock) {
        Stock entity = dao.findById(stock.getId());

        if(entity != null)
        {
            entity.setProductCode(stock.getProductCode());
            entity.setStock(stock.getStock());
            entity.setWarehouse(stock.getWarehouse());
            entity.setStatus(stock.getStatus());
            entity.setImportOrigin(stock.getImportOrigin());
            entity.setProcessingDate(stock.getProcessingDate());
            entity.setFeedStatus(stock.getFeedStatus());
            entity.setErrorDescription(stock.getErrorDescription());
            entity.setCompany(stock.getCompany());
        }
    }

    @Override
    public StockReport getReport() {
        StockReport report = new StockReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
}
