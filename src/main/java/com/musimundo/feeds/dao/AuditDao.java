package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.utilities.FeedType;

import java.util.List;

public interface AuditDao
{
    Audit findById(int id);

    List<Audit> findBy(String productCode);

    List<Audit> findBy(FeedType feedType);

    List<Audit> findBy(String productCode, FeedType feedType);

    List<Audit> findBy(String productCode, FeedType feedType, String importOrigin);

    List<Audit> findBy(String productCode, FeedType feedType, String importOrigin, String warehouse);

    List<Audit> findAll();

    void save(Audit audit);

	boolean updateStateByTypeAndImport(FeedType feedType, String importOrigin);
}
