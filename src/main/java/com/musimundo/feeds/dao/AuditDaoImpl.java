package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.utilities.FeedType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("auditDao")
public class AuditDaoImpl extends AbstractDao <Integer, Audit> implements AuditDao {

    @Override
    public Audit findById(int id) {
        Audit res = getByKey(id);
        return res;
    }

    @Override
    public List<Audit> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("codigoProducto", productCode));

        List<Audit> res = crit.list();

        return res;
    }

    @Override
    public List<Audit> findByFeedType(FeedType feedType) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("feedType", feedType));
        List<Audit> res = criteria.list();

        return res;
    }

    @Override
    public List<Audit> findAll() {
        Criteria crit = createEntityCriteria();
        List<Audit> res = crit.list();

        return res;
    }

    @Override
    public void save(Audit audit) {
        persist(audit);
    }
}
