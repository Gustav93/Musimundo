package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Media;

import java.util.List;

public interface MediaService
{
    Media findById(int id);

    List<Media> findByProductCode(String productCode);

    List<Media> findAll();

    void save(Media media);

    void update(Media media);
}
