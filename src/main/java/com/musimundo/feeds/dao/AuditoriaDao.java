package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Auditoria;

import java.util.List;

public interface AuditoriaDao
{
    Auditoria findById(int id);

    List<Auditoria> findByProductCode(String productCode);

    List<Auditoria> findAll();

    void save(Auditoria c);
}
