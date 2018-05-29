package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.OldProduct;

import java.util.List;

public interface Dao {

    List<OldProduct> findAll();
}
