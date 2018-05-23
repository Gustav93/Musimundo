package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Media;
import com.musimundo.feeds.beans.Stock;
import com.musimundo.utilities.FeedStatus;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mediaDao")
public class MediaDaoImpl extends AbstractDao <Integer, Media> implements MediaDao
{
    @Override
    public Media findById(int id) {
        Media res = findById(id);

        return res;
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
    public void save(Media media) {
        persist(media);
    }

    @Override
    public Long countAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();

        return res;
    }

    @Override
    public Long count(FeedStatus status) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("feedStatus", status));
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();

        return res;
    }
}
