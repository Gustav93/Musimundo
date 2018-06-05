package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Media;
import com.musimundo.feeds.beans.MediaReport;
import com.musimundo.feeds.dao.MediaDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("mediaService")
@Transactional
public class MediaServiceImpl implements MediaService
{
    @Autowired
    MediaDao dao;

    @Override
    public Media findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Media> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Media> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Media> findNotProcessed() {
        return dao.findNotProcessed();
    }

    @Override
    public void save(Media media) {
        dao.save(media);
    }

    @Override
    public void update(Media media) {

        Media entity = dao.findById(media.getId());

        if(entity != null)
        {
            entity.setProductCode(media.getProductCode());
            entity.setCodeMedia(media.getCodeMedia());
            entity.setIsDefault(media.getIsDefault());
            entity.setImportOrigin(media.getImportOrigin());
            entity.setProcessingDate(media.getProcessingDate());
            entity.setFeedStatus(media.getFeedStatus());
            entity.setErrorDescription(media.getErrorDescription());
            entity.setCompany(media.getCompany());
        }
    }

    @Override
    public MediaReport getReport() {
        MediaReport report = new MediaReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
    
    @Override
    public MediaReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	MediaReport report = new MediaReport();
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
    public MediaReport getReportByCode(String code) {
    	MediaReport report = new MediaReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Media> findMediaByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findMediaByDate(desde, hasta);
	}
}
