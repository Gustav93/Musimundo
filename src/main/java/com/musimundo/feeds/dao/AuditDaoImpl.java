package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.utilities.FeedType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
    public List<Audit> findBy(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("productCode", productCode));

        List<Audit> res = crit.list();

        return res;
    }

    @Override
    public List<Audit> findBy(FeedType feedType) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("feedType", feedType));
        criteria.add(Restrictions.eq("processed", false));
        List<Audit> res = criteria.list();

        return res;
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCode", productCode));
        criteria.add(Restrictions.eq("feedType", feedType));
        criteria.add(Restrictions.eq("processed", false));

        return criteria.list();
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType, String importOrigin) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCode", productCode));
        criteria.add(Restrictions.eq("feedType", feedType));
        criteria.add(Restrictions.eq("importOrigin", importOrigin));
        criteria.add(Restrictions.eq("processed", false));
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.desc("id")).setMaxResults(1);

        return criteria.list();
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType, String importOrigin, String warehouse) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCode", productCode));
        criteria.add(Restrictions.eq("feedType", feedType));
        criteria.add(Restrictions.eq("importOrigin", importOrigin));
        criteria.add(Restrictions.eq("warehouseStock", warehouse));
        criteria.add(Restrictions.eq("processed", false));
        criteria.addOrder(Order.desc("id")).setMaxResults(1);

        return criteria.list();
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
    
    @Override
	public boolean updateStateByTypeAndImport(FeedType feedType, String importOrigin) {
		Session sessionNew = null;
		try{
			sessionNew = getSessionFactory().openSession();
			Query query = sessionNew.createQuery("UPDATE Audit SET processed = 1 where processed=0 and "+"FEED_TYPE="+feedType.ordinal()+" and IMPORT_ORIGIN='"+importOrigin+"'");
			query.executeUpdate();
			sessionNew.clear();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			sessionNew.close();
		}				
		return true;		
	}
    
}
