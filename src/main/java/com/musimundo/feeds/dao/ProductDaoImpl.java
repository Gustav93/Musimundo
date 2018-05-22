package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Product;
import com.musimundo.utilities.EstadoProcesamiento;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("productoDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao
{
    @Override
    public Product findById(int id)
    {
        Product res = getByKey(id);
        return res;
    }

    @Override
    public List<Product> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Product> res = (List<Product>) crit.list();

        return res;
    }

    @Override
    public List<Product> findAll() {
        Criteria crit = createEntityCriteria();
        List<Product> res = (List<Product>) crit.list();

        return res;
    }

    @Override
    public void save(Product product) {
        persist(product);
    }

    @Override
    public Long countAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();

        return res;
    }

    @Override
    public Long count(EstadoProcesamiento status) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("estadoProcesamiento", status));
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();

        return res;
    }
}