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
    public void save(Media media) {
        dao.save(media);
    }

    @Override
    public void update(Media media) {

        Media entity = dao.findById(media.getId());

        if(entity != null)
        {
            entity.setCodigoProducto(media.getCodigoProducto());
            entity.setCodeMedia(media.getCodeMedia());
            entity.setIsDefault(media.getIsDefault());
            entity.setOrigenImportacion(media.getOrigenImportacion());
            entity.setFechaProcesamiento(media.getFechaProcesamiento());
            entity.setEstadoProcesamiento(media.getEstadoProcesamiento());
            entity.setDescripcionError(media.getDescripcionError());
            entity.setEmpresa(media.getEmpresa());
        }
    }
}
