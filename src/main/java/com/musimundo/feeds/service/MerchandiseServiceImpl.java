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
    public void save(Merchandise m) {
        dao.save(m);
    }

    @Override
    public void update(Merchandise m) {

        Merchandise entity = dao.findById(m.getId());

        if(entity != null)
        {
            entity.setSource(m.getSource());
            entity.setRefType(m.getRefType());
            entity.setTarget(m.getTarget());
            entity.setRelacion(m.getRelacion());
            entity.setQualifier(m.getQualifier());
            entity.setPreselected(m.getPreselected());
            entity.setOrigenImportacion(m.getOrigenImportacion());
            entity.setFechaProcesamiento(m.getFechaProcesamiento());
            entity.setEstadoProcesamiento(m.getEstadoProcesamiento());
            entity.setDescripcionError(m.getDescripcionError());
            entity.setEmpresa(m.getEmpresa());
        }
    }
}
