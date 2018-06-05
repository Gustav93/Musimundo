package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.feeds.beans.MerchandiseReport;
import com.musimundo.feeds.dao.MerchandiseDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("merchandiseService")
@Transactional
public class MerchandiseServiceImpl implements MerchandiseService {

    @Autowired
    MerchandiseDao dao;

    @Override
    public Merchandise findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Merchandise> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Merchandise> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Merchandise> findNotProcessed() {
        return dao.findNotProcessed();
    }

    @Override
    public void save(Merchandise merchandise) {
        dao.save(merchandise);
    }

    @Override
    public void update(Merchandise merchandise) {

        Merchandise entity = dao.findById(merchandise.getId());

        if(entity != null)
        {
            entity.setSource(merchandise.getSource());
            entity.setRefType(merchandise.getRefType());
            entity.setTarget(merchandise.getTarget());
            entity.setRelationship(merchandise.getRelationship());
            entity.setQualifier(merchandise.getQualifier());
            entity.setPreselected(merchandise.getPreselected());
            entity.setImportOrigin(merchandise.getImportOrigin());
            entity.setProcessingDate(merchandise.getProcessingDate());
            entity.setFeedStatus(merchandise.getFeedStatus());
            entity.setErrorDescription(merchandise.getErrorDescription());
            entity.setCompany(merchandise.getCompany());
        }
    }

    @Override
    public MerchandiseReport getReport() {
        MerchandiseReport report = new MerchandiseReport();

        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
    
    @Override
    public MerchandiseReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	MerchandiseReport report = new MerchandiseReport();
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
    public MerchandiseReport getReportByCode(String code) {
    	MerchandiseReport report = new MerchandiseReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Merchandise> findMerchandiseByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findMerchandiseByDate(desde, hasta);
	}
}
