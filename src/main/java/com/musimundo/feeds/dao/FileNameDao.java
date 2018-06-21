package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.FileName;

public interface FileNameDao {

    FileName findById(int id);

    FileName findByName(String name);

    void save(FileName fileName);
}
