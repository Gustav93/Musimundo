package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Media;
import com.musimundo.feeds.beans.Precio;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MediaDao")
public class MediaDaoImpl extends AbstractDao <Integer, Media> implements MediaDao
{
    @Override
    public Media findById(int id) {
        Media m = findById(id);

        return m;
    }

    @Override
    public List<Media> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));
        List<Media> res = crit.list();

        return res;
    }

    @Override
    public List<Media> findAll() {
        Criteria crit = createEntityCriteria();
        List<Media> res = crit.list();

        return res;
    }

    @Override
    public void save(Media m) {
        persist(m);
    }
}
