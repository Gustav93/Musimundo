package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Media;

import java.util.List;

public interface MediaDao {

    Media findById(int id);

    List<Media> findByProductCode(String productCode);

    List<Media> findAll();

    void save(Media media);
}
