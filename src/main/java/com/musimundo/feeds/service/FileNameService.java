package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.FileName;

public interface FileNameService {

    FileName findById(int id);

    FileName findByName(String name);

    void save(FileName fileName);

    boolean exists(String name);
}
