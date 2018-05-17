package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Precio;
import com.musimundo.feeds.dao.PrecioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("priceService")
@Transactional
public class PriceServiceImpl implements PriceService {

    @Autowired
    PrecioDao dao;

    @Override
    public Precio findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Precio> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Precio> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Precio p) {
        dao.save(p);
    }

    @Override
    public void update(Precio p) {

        Precio entity = dao.findById(p.getId());

        if(entity != null)
        {
            entity.setCodigoProducto(p.getCodigoProducto());
            entity.setCurrency(p.getCurrency());
            entity.setOnlinePrice(p.getOnlinePrice());
            entity.setStorePrice(p.getStorePrice());
            entity.setHasPriority(p.getHasPriority());
            entity.setOrigenImportacion(p.getOrigenImportacion());
            entity.setFechaProcesamiento(p.getFechaProcesamiento());
            entity.setEstadoProcesamiento(p.getEstadoProcesamiento());
            entity.setDescripcionError(p.getDescripcionError());
            entity.setEmpresa(p.getEmpresa());
        }
    }
}
