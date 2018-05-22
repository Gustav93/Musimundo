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
    public void save(Stock stock) {
        dao.save(stock);
    }

    @Override
    public void update(Stock stock) {
        Stock entity = dao.findById(stock.getId());

        if(entity != null)
        {
            entity.setCodigoProducto(stock.getCodigoProducto());
            entity.setStock(stock.getStock());
            entity.setWarehouse(stock.getWarehouse());
            entity.setStatus(stock.getStatus());
            entity.setOrigenImportacion(stock.getOrigenImportacion());
            entity.setFechaProcesamiento(stock.getFechaProcesamiento());
            entity.setEstadoProcesamiento(stock.getEstadoProcesamiento());
            entity.setDescripcionError(stock.getDescripcionError());
            entity.setEmpresa(stock.getEmpresa());
        }
    }
}
