package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.feeds.dao.AuditDao;
import com.musimundo.utilities.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Audit> findByFeedType(FeedType feedType) {
        return dao.findByFeedType(feedType);
    }

    @Override
    public List<Audit> findAll() {
        return dao.findAll();
    }
}
