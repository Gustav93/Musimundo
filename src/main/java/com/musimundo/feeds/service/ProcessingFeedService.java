package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.*;
import com.musimundo.utilities.FeedType;

import java.util.List;

public interface ProcessingFeedService {
//    void process(FeedType feedType);

    List<Product> processProduct();

    List<Price> processPrice();

    List<Stock> processStock();

    List<Media> processMedia();

    List<Merchandise> processMerchandise();

    List<Classification> processClassification();
}
