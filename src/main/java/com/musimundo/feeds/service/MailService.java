package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.*;
import com.musimundo.utilities.Company;

import java.io.File;
import java.util.List;

public interface MailService {

    void sendProductMail(List<ProductReport> productReportList, File attachment, Company company);

    void sendPriceMail(List<PriceReport> priceReportList, File attachment, Company company);

    void sendStockMail(List<StockReport> stockReportList, File attachment, Company company);

    void sendMediaMail(List<MediaReport> mediaReportList, File attachment, Company company);

    void sendMerchandiseMail(List<MerchandiseReport> merchandiseReportList, File attachment, Company company);

    void sendClassificationMail(List<ClassificationReport> classificationReportList, File attachment, Company company);
}
