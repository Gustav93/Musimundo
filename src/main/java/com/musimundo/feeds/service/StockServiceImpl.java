package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.beans.StockReport;
import com.musimundo.feeds.dao.StockDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
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
    public List<Stock> findNotProcessed() {
        return dao.findNotProcessed();
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
    
    @Override
    public StockReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	StockReport report = new StockReport();
        //control si fechahasta es nula o igual a fecha desde
        if(fechaHasta == null || fechaHasta.equals(fechaDesde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(fechaDesde); 
        	dateAfter.add(Calendar.DATE, 1);
        	fechaHasta = dateAfter.getTime();
        }
        
        report.setCountTotal(dao.countAllByDate(fechaDesde, fechaHasta));
        report.setCountOk(dao.countByDate(fechaDesde, fechaHasta, FeedStatus.OK));
        report.setCountWarning(dao.countByDate(fechaDesde, fechaHasta, FeedStatus.WARNING));
        report.setCountError(dao.countByDate(fechaDesde, fechaHasta, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByDate(fechaDesde, fechaHasta, FeedStatus.NOT_PROCESSED));

        return report;
    }
    
    @Override
    public StockReport getReportByCode(String code) {
    	StockReport report = new StockReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Stock> findStockByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findStockByDate(desde, hasta);
	}
}
