package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Media;
import com.musimundo.feeds.dao.MediaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("mediaService")
@Transactional
public class MediaServiceImpl implements MediaService
{
    @Autowired
    MediaDao dao;

    @Override
    public Media findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Media> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Media> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Media m) {
        dao.save(m);
    }

    @Override
    public void update(Media m) {

        Media entity = dao.findById(m.getId());

        if(entity != null)
        {
            entity.setCodigoProducto(m.getCodigoProducto());
            entity.setCodeMedia(m.getCodeMedia());
            entity.setIsDefault(m.getIsDefault());
            entity.setOrigenImportacion(m.getOrigenImportacion());
            entity.setFechaProcesamiento(m.getFechaProcesamiento());
            entity.setEstadoProcesamiento(m.getEstadoProcesamiento());
            entity.setDescripcionError(m.getDescripcionError());
            entity.setEmpresa(m.getEmpresa());
        }
    }
}
