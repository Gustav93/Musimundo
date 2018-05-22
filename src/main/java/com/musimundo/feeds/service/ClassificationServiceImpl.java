package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Classification;
import com.musimundo.feeds.dao.ClassificationDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClassificationServiceImpl implements ClassificationService {

    @Autowired
    ClassificationDao dao;

    @Override
    public Classification findById(int id) {
        return null;
    }

    @Override
    public List<Classification> findByProductCode(String productCode) {
        return null;
    }

    @Override
    public List<Classification> findAll() {
        return null;
    }

    @Override
    public void save(Classification classification) {

    }

    @Override
    public void update(Classification classification) {

    }
}
