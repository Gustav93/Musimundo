package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Media;
import com.musimundo.utilities.FeedStatus;

import java.util.List;

public interface MediaDao {

    Media findById(int id);

    List<Media> findByProductCode(String productCode);

    List<Media> findAll();

    void save(Media media);

    Long countAll();

    Long count(FeedStatus status);
}
