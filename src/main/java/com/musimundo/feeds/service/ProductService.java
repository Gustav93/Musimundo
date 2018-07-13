package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Product;
import com.musimundo.feeds.beans.ProductReport;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;

import java.io.File;
import java.text.ParseException;
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

//    ProductReport getReport(List<Product> productList);

	ProductReport getReportByDate(Date fechaDesde, Date fechaHasta);

	ProductReport getReportByCode(String code);

	List<Product> findProductByDate(Date desde, Date hasta);

    File getCsv(List<Product> productList, Filter filter);

    ProductReport getReport(List<Product> productList, String importOrigin);

    ProductReport getReport(List<Product> productList);
	
	void insertValues(List<Product> products) throws ParseException;

    List<ProductReport> getReportList(List<Product> productList);

    List<String> getImportOrigin(List<Product> productList);

    Company getCompany(List<Product> productList);

    boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company);

    List<Product> cloneProductList(List<Product> productList);
}
