package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.feeds.dao.AuditDao;
import com.musimundo.utilities.ErrorType;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("auditService")
@Transactional
public class AuditServiceImpl implements AuditService {

    @Autowired
    AuditDao dao;

    @Override
    public Audit findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Audit> findByProductCode(String productCode) {
        return dao.findBy(productCode);
    }

    @Override
    public List<Audit> findBy(FeedType feedType) {
        return dao.findBy(feedType);
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType) {
        return dao.findBy(productCode, feedType);
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType, String importOrigin, String warehouse) {
        return dao.findBy(productCode, feedType, importOrigin, warehouse);
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType, String importOrigin) {
        return dao.findBy(productCode, feedType, importOrigin);
    }

    @Override
    public List<Audit> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Audit audit) {
        dao.save(audit);
    }

    @Override
    public ErrorType getErrorType(Audit audit)
    {
        String errorCode = audit.getErrorCode();

        if(errorCode.contains("E"))
            return ErrorType.ERROR;

        else if(errorCode.contains("W"))
            return ErrorType.WARNING;

        else if(errorCode.contains("I"))
            return  ErrorType.SUCCESS;

        else
            throw new IllegalArgumentException("illegal error type");
    }

    @Override
    public void setWarehouseStock(Audit audit) {

        if(!audit.getFeedType().equals(FeedType.STOCK))
            return;

        String res = null;
        Pattern pattern = Pattern.compile("(carsa_\\w{1,4})|(emsa_\\w{1,4})");
        Matcher matcher = pattern.matcher(audit.getDescription());

        if(matcher.find())
            res = matcher.group();

        audit.setWarehouseStock(res);
//        return res;
    }

    @Override
    public void update(Audit audit) {
        Audit entity = dao.findById(audit.getId());

        entity.setAuditLevel(audit.getAuditLevel());
        entity.setAuditType(audit.getAuditType());
        entity.setAuditDate(audit.getAuditDate());
        entity.setErrorCode(audit.getErrorCode());
        entity.setDescription(audit.getDescription());
        entity.setCompany(audit.getCompany());
        entity.setProductCode(audit.getProductCode());
        entity.setImportOrigin(audit.getImportOrigin());
        entity.setFeedType(audit.getFeedType());
        entity.setProcessed(audit.getProcessed());
    }
}
