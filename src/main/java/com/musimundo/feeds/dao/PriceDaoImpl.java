package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Price;
import com.musimundo.utilities.FeedStatus;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("priceDao")
public class PriceDaoImpl extends AbstractDao <Integer, Price> implements PriceDao
{

    @Override
    public Price findById(int id) {
        Price res = getByKey(id);
        return res;
    }

    @Override
    public List<Price> findByProductCode(String productCode) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCode", productCode));

        List<Price> res = criteria.list();

        return res;
    }

    @Override
    public List<Price> findAll() {
        Criteria criteria = createEntityCriteria();
        List<Price> res = criteria.list();
        return res;
    }

    @Override
    public void save(Price price) {
        persist(price);
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