package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Product;
import com.musimundo.feeds.beans.ProductReport;
import com.musimundo.feeds.dao.ProductDao;
import com.musimundo.utilities.FeedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Override
    public Product findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Product> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Product> findAll() {
        Long r = dao.count(FeedStatus.NOT_PROCESSED);
        System.out.println(r);
        return dao.findAll();
    }

    @Override
    public void save(Product product) {
        dao.save(product);
    }

    public void update(Product product)
    {
        Product entity = dao.findById(product.getId());

         if(entity != null)
         {
             entity.setProductCode(product.getProductCode());
             entity.setEan(product.getEan());
             entity.setBrand(product.getBrand());
             entity.setName(product.getName());
             entity.setCategory(product.getCategory());
             entity.setWeight(product.getWeight());
             entity.setOnlineDateTime(product.getOnlineDateTime());
             entity.setOfflineDateTime(product.getOfflineDateTime());
             entity.setApprovalStatus(product.getApprovalStatus());
             entity.setDescription(product.getDescription());
             entity.setImportOrigin(product.getImportOrigin());
             entity.setProcessingDate(product.getProcessingDate());
             entity.setFeedStatus(product.getFeedStatus());
             entity.setErrorDescription(product.getErrorDescription());
             entity.setCompany(product.getCompany());
         }
    }

    @Override
    public ProductReport getReport() {
        ProductReport report = new ProductReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
}
