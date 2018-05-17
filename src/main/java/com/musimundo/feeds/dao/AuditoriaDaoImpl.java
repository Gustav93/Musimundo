package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Auditoria;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AuditoriaDao")
public class AuditoriaDaoImpl extends AbstractDao <Integer, Auditoria> implements AuditoriaDao {

    @Override
    public Auditoria findById(int id) {
        Auditoria res = getByKey(id);
        return res;
    }

    @Override
    public List<Auditoria> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Auditoria> res = crit.list();

        return res;
    }

    @Override
    public List<Auditoria> findAll() {
        Criteria crit = createEntityCriteria();
        List<Auditoria> res = crit.list();

        return res;
    }

    @Override
    public void save(Auditoria a) {
        persist(a);
    }
}
