package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Stock;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StockDao")
public class StockDaoImpl extends AbstractDao <Integer, Stock> implements StockDao
{

    @Override
    public Stock findById(int id) {
        Stock res = getByKey(id);

        return res;
    }

    @Override
    public List<Stock> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Stock> res = crit.list();

        return res;
    }

    @Override
    public List<Stock> findAll() {
        Criteria crit = createEntityCriteria();

        List<Stock> res = crit.list();

        return res;
    }

    @Override
    public void save(Stock s) {
        persist(s);
    }
}
