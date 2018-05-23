package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Classification;
import com.musimundo.utilities.FeedStatus;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("classificationDao")
public class ClassificationDaoImpl extends AbstractDao <Integer, Classification> implements ClassificationDao {

    @Override
    public Classification findById(int id) {
        Classification res = getByKey(id);

        return res;
    }

    @Override
    public List<Classification> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Classification> res = crit.list();

        return res;
    }

    @Override
    public List<Classification> findAll() {
        Criteria crit = createEntityCriteria();
        List<Classification> res = crit.list();

        return res;
    }

    @Override
    public void save(Classification classification) {
        persist(classification);
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
