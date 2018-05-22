package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Price;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("precioDao")
public class PriceDaoImpl extends AbstractDao <Integer, Price> implements PriceDao
{

    @Override
    public Price findById(int id) {
        Price res = getByKey(id);
        return res;
    }

    @Override
    public List<Price> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Price> res = crit.list();

        return res;
    }

    @Override
    public List<Price> findAll() {
        Criteria crit = createEntityCriteria();
        List<Price> res = crit.list();
        return res;
    }

    @Override
    public void save(Price price) {
        persist(price);
    }
}
