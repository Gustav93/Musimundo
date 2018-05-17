package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Producto;
import com.musimundo.feeds.dao.ProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("ProductService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductoDao dao;

    @Override
    public Producto findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Producto> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Producto> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Producto p) {
        dao.save(p);
    }

    public void update(Producto p)
    {
        Producto entity = dao.findById(p.getId());

         if(entity != null)
         {
             entity.setCodigoProducto(p.getCodigoProducto());
             entity.setEan(p.getEan());
             entity.setBrand(p.getBrand());
             entity.setName(p.getName());
             entity.setCategory(p.getCategory());
             entity.setWeight(p.getWeight());
             entity.setOnlineDateTime(p.getOnlineDateTime());
             entity.setOfflineDateTime(p.getOfflineDateTime());
             entity.setApprovalStatus(p.getApprovalStatus());
             entity.setDescription(p.getDescription());
             entity.setOrigenImportacion(p.getOrigenImportacion());
             entity.setFechaProcesamiento(p.getFechaProcesamiento());
             entity.setEstadoProcesamiento(p.getEstadoProcesamiento());
             entity.setDescripcionError(p.getDescripcionError());
             entity.setEmpresa(p.getEmpresa());
         }
    }
}
