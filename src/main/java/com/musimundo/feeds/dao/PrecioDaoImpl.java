package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Precio;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PrecioDao")
public class PrecioDaoImpl extends AbstractDao <Integer, Precio> implements PrecioDao
{

    @Override
    public Precio findById(int id) {
        Precio res = getByKey(id);
        return res;
    }

    @Override
    public List<Precio> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Precio> res = crit.list();

        return res;
    }

    @Override
    public List<Precio> findAll() {
        Criteria crit = createEntityCriteria();
        List<Precio> res = crit.list();
        return res;
    }

    @Override
    public void save(Precio p) {
        persist(p);
    }
}
