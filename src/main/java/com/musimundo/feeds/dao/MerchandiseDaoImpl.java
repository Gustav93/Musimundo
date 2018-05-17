package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Merchandise;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MerchandiseDao")
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
    public void save(Merchandise m) {
        persist(m);
    }
}
