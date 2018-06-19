package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.FileName;
import com.musimundo.feeds.dao.FileNameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fileNameService")
@Transactional
public class FileNameServiceImpl implements FileNameService {

    @Autowired
    FileNameDao dao;

    @Override
    public FileName findById(int id) {
        return dao.findById(id);
    }

    @Override
    public FileName findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public void save(FileName fileName) {
        dao.save(fileName);
    }

    @Override
    public boolean exists(String name) {
        FileName fileName = dao.findByName(name);
        boolean res = false;

        if(fileName != null)
            res = true;

        return res;
    }
}
