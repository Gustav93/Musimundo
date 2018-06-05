package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Price;
import com.musimundo.feeds.beans.PriceReport;
import com.musimundo.feeds.dao.PriceDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
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
    public List<Price> findNotProcessed() {
        return dao.findNotProcessed();
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
    
    @Override
    public PriceReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	PriceReport report = new PriceReport();
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
    public PriceReport getReportByCode(String code) {
    	PriceReport report = new PriceReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Price> findPriceByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findPriceByDate(desde, hasta);
	}
}
