package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.utilities.FeedType;

import java.util.List;

public interface AuditService {
    Audit findById(int id);

    List<Audit> findByProductCode(String productCode);

    List<Audit> findByFeedType(FeedType feedType);

    List<Audit> findAll();
}
