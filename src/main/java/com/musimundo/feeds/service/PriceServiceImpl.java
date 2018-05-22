package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Price;
import com.musimundo.feeds.dao.PriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("priceService")
@Transactional
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceDao dao;

    @Override
    public Price findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Price> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Price> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Price price) {
        dao.save(price);
    }

    @Override
    public void update(Price price) {

        Price entity = dao.findById(price.getId());

        if(entity != null)
        {
            entity.setCodigoProducto(price.getCodigoProducto());
            entity.setCurrency(price.getCurrency());
            entity.setOnlinePrice(price.getOnlinePrice());
            entity.setStorePrice(price.getStorePrice());
            entity.setHasPriority(price.getHasPriority());
            entity.setOrigenImportacion(price.getOrigenImportacion());
            entity.setFechaProcesamiento(price.getFechaProcesamiento());
            entity.setEstadoProcesamiento(price.getEstadoProcesamiento());
            entity.setDescripcionError(price.getDescripcionError());
            entity.setEmpresa(price.getEmpresa());
        }
    }
}
