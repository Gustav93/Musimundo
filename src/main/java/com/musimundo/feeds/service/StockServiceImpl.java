package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.dao.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("stockService")
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    StockDao dao;

    @Override
    public Stock findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Stock> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Stock> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Stock s) {
        dao.save(s);
    }

    @Override
    public void update(Stock s) {
        Stock entity = dao.findById(s.getId());

        if(entity != null)
        {
            entity.setCodigoProducto(s.getCodigoProducto());
            entity.setStock(s.getStock());
            entity.setWarehouse(s.getWarehouse());
            entity.setStatus(s.getStatus());
            entity.setOrigenImportacion(s.getOrigenImportacion());
            entity.setFechaProcesamiento(s.getFechaProcesamiento());
            entity.setEstadoProcesamiento(s.getEstadoProcesamiento());
            entity.setDescripcionError(s.getDescripcionError());
            entity.setEmpresa(s.getEmpresa());
        }
    }
}
