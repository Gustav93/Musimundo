package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Audit;

import java.util.List;

public interface AuditDao
{
    Audit findById(int id);

    List<Audit> findByProductCode(String productCode);

    List<Audit> findAll();

    void save(Audit audit);
}
