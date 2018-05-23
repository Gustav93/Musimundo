package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.utilities.FeedStatus;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("merchandiseDao")
public class MerchandiseDaoImpl  extends AbstractDao <Integer, Merchandise> implements MerchandiseDao {

    @Override
    public Merchandise findById(int id) {
        Merchandise res = getByKey(id);

        return res;
    }

    @Override
    public List<Merchandise> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));
        List<Merchandise> res = crit.list();

        return res;
    }

    @Override
    public List<Merchandise> findAll() {
        Criteria crit = createEntityCriteria();
        List<Merchandise> res = crit.list();

        return res;
    }

    @Override
    public void save(Merchandise merchandise) {
        persist(merchandise);
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
