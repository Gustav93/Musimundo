package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Media;
import com.musimundo.feeds.beans.MediaReport;
import com.musimundo.feeds.dao.MediaDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
