package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.utilities.ErrorType;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.FeedType;

import java.util.List;

public interface AuditService {
    Audit findById(int id);

    List<Audit> findByProductCode(String productCode);

    List<Audit> findBy(FeedType feedType);

    List<Audit> findBy(String productCode, FeedType feedType);

    List<Audit> findBy(String productCode, FeedType feedType, String importOrigin);

    List<Audit> findAll();

    void save(Audit audit);

    void update(Audit audit);

    ErrorType getErrorType(Audit audit);

}
