package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Producto;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("ProductoDao")
public class ProductoDaoImpl extends AbstractDao<Integer, Producto> implements ProductoDao
{
    @Override
    public Producto findById(int id)
    {
        Producto res = getByKey(id);
        return res;
    }

    @Override
    public List<Producto> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Producto> res = (List<Producto>) crit.list();

        return res;
    }

    @Override
    public List<Producto> findAll()
    {
        Criteria crit = createEntityCriteria();
        List<Producto> res = (List<Producto>) crit.list();

        return res;
    }

    @Override
    public void save(Producto p) {
        persist(p);
    }
}