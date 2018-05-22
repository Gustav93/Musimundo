package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Product;
import com.musimundo.feeds.beans.ProductReport;
import com.musimundo.feeds.dao.ProductDao;
import com.musimundo.utilities.EstadoProcesamiento;
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
        Long r = dao.count(EstadoProcesamiento.NO_PROCESADO);
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
             entity.setCodigoProducto(product.getCodigoProducto());
             entity.setEan(product.getEan());
             entity.setBrand(product.getBrand());
             entity.setName(product.getName());
             entity.setCategory(product.getCategory());
             entity.setWeight(product.getWeight());
             entity.setOnlineDateTime(product.getOnlineDateTime());
             entity.setOfflineDateTime(product.getOfflineDateTime());
             entity.setApprovalStatus(product.getApprovalStatus());
             entity.setDescription(product.getDescription());
             entity.setOrigenImportacion(product.getOrigenImportacion());
             entity.setFechaProcesamiento(product.getFechaProcesamiento());
             entity.setEstadoProcesamiento(product.getEstadoProcesamiento());
             entity.setDescripcionError(product.getDescripcionError());
             entity.setEmpresa(product.getEmpresa());
         }
    }

    @Override
    public ProductReport getReport() {
        ProductReport report = new ProductReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(EstadoProcesamiento.PROCESADO_CORRECTAMENTE));
        report.setCountWarning(dao.count(EstadoProcesamiento.PROCESADO_CON_WARNING));
        report.setCountError(dao.count(EstadoProcesamiento.PROCESADO_CON_ERROR));
        report.setCountNotProcessed(dao.count(EstadoProcesamiento.NO_PROCESADO));

        return report;
    }
}
