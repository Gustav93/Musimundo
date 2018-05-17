package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Clasificacion;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ClasificacionDao")
public class ClasificacionDaoImpl extends AbstractDao <Integer, Clasificacion> implements ClasificacionDao {

    @Override
    public Clasificacion findById(int id) {
        Clasificacion res = getByKey(id);

        return res;
    }

    @Override
    public List<Clasificacion> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Clasificacion> res = crit.list();

        return res;
    }

    @Override
    public List<Clasificacion> findAll() {
        Criteria crit = createEntityCriteria();
        List<Clasificacion> res = crit.list();

        return res;
    }

    @Override
    public void save(Clasificacion c) {
        persist(c);
    }
}
