package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.utilities.FeedType;

import java.util.List;

public interface AuditDao
{
    Audit findById(int id);

    List<Audit> findByProductCode(String productCode);

    List<Audit> findByFeedType(FeedType feedType);

    List<Audit> findAll();

    void save(Audit audit);
}
