package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.feeds.dao.MerchandiseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("merchandiseService")
@Transactional
public class MerchandiseServiceImpl implements MerchandiseService {

    @Autowired
    MerchandiseDao dao;

    @Override
    public Merchandise findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Merchandise> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Merchandise> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Merchandise merchandise) {
        dao.save(merchandise);
    }

    @Override
    public void update(Merchandise merchandise) {

        Merchandise entity = dao.findById(merchandise.getId());

        if(entity != null)
        {
            entity.setSource(merchandise.getSource());
            entity.setRefType(merchandise.getRefType());
            entity.setTarget(merchandise.getTarget());
            entity.setRelacion(merchandise.getRelacion());
            entity.setQualifier(merchandise.getQualifier());
            entity.setPreselected(merchandise.getPreselected());
            entity.setOrigenImportacion(merchandise.getOrigenImportacion());
            entity.setFechaProcesamiento(merchandise.getFechaProcesamiento());
            entity.setEstadoProcesamiento(merchandise.getEstadoProcesamiento());
            entity.setDescripcionError(merchandise.getDescripcionError());
            entity.setEmpresa(merchandise.getEmpresa());
        }
    }
}
