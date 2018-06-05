package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Product;
import com.musimundo.feeds.beans.ProductReport;

import java.util.Date;
import java.util.List;

public interface ProductService
{
    Product findById(int id);

    List<Product> findByProductCode(String productCode);

    List<Product> findAll();

    List<Product> findNotProcessed();

    void save(Product product);

    void update(Product product);

    ProductReport getReport();

	ProductReport getReportByDate(Date fechaDesde, Date fechaHasta);

	ProductReport getReportByCode(String code);

	List<Product> findProductByDate(Date desde, Date hasta);
}
