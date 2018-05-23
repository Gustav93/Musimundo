package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.utilities.FeedStatus;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stockDao")
public class StockDaoImpl extends AbstractDao <Integer, Stock> implements StockDao
{

    @Override
    public Stock findById(int id) {
        Stock res = getByKey(id);

        return res;
    }

    @Override
    public List<Stock> findByProductCode(String productCode) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCode", productCode));

        List<Stock> res = criteria.list();

        return res;
    }

    @Override
    public List<Stock> findAll() {
        Criteria criteria = createEntityCriteria();

        List<Stock> res = criteria.list();

        return res;
    }

    @Override
    public void save(Stock stock) {
        persist(stock);
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
