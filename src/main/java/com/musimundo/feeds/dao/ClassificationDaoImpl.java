package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Classification;
import com.musimundo.feeds.beans.Product;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Utils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.rmi.CORBA.Util;
import java.util.Date;
import java.util.List;

@Repository("classificationDao")
public class ClassificationDaoImpl extends AbstractDao <Integer, Classification> implements ClassificationDao {

    @Override
    public Classification findById(int id) {
        Classification res = getByKey(id);

        return res;
    }

    @Override
    public List<Classification> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("productCode", productCode));

        List<Classification> res = crit.list();

        return res;
    }

    @Override
    public List<Classification> findAll() {
        Criteria crit = createEntityCriteria();
        List<Classification> res = crit.list();

        return res;
    }

    @Override
    public List<Classification> findNotProcessed() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("processed", false));
        return criteria.list();
    }

    @Override
    public void save(Classification classification) {
        persist(classification);
    }

    @Override
    public Long countAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();

        return res;
    }

    @Override
    public Long count(FeedStatus status) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("feedStatus", status));
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();

        return res;
    }
    
    @Override
	public Long countAllByDate(Date fechaDesde, Date fechaHasta) {
		 Criteria criteria = createEntityCriteria();
		 criteria.add(Restrictions.between("processingDate", fechaDesde, fechaHasta));
	     criteria.setProjection(Projections.rowCount());
	     Long res = (Long)criteria.uniqueResult();

        return res;
	}

	@Override
	public Long countByDate(Date fechaDesde, Date fechaHasta, FeedStatus status) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("feedStatus", status));
        criteria.add(Restrictions.between("processingDate", fechaDesde, fechaHasta));
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();
		return res;
	}
	
	@Override
	public Long countByCode(String code, FeedStatus status) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("feedStatus", status));
        criteria.add(Restrictions.eq("productCode", code));
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();
		return res;
	}

	@Override
	public Long countAllByCode(String code) {
		 Criteria criteria = createEntityCriteria();
	        criteria.add(Restrictions.eq("productCode", code));
	        criteria.setProjection(Projections.rowCount());
	        Long res = (Long)criteria.uniqueResult();
	        
		return res;
	}

	@Override
	public List<Classification> findClassificationByDate(Date desde, Date hasta) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.between("processingDate", desde, hasta));

        List<Classification> res = (List<Classification>) criteria.list();

        return res;
	}
	
	@Override
	public Long countByFeed(String feedName, FeedStatus status) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("feedStatus", status));
        criteria.add(Restrictions.eq("importOrigin", feedName));
        criteria.setProjection(Projections.rowCount());
        Long res = (Long)criteria.uniqueResult();
		return res;
	}
	
	@Override
	public List<String> findFeedsByDate(Date desde, Date hasta, String empresa) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("company", empresa));
		criteria.add(Restrictions.between("processingDate", desde, hasta));
		criteria.setProjection(Projections.property("importOrigin"));
		criteria.addOrder(Order.asc("importOrigin"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<String> res = (List<String>) criteria.list();

        return res;
	}
	
	@Override
    public boolean insertClassificationlist(String insert) {
    	
    	Session sessionNew = null;
		try{
			sessionNew = getSessionFactory().openSession();
			Query query = sessionNew.createSQLQuery(insert);
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
	
	@Override
	public boolean updateState(FeedStatus status, String errorDescription, String company) {
		Session sessionNew = null;
		String date = Utils.getDateString(new Date());
		try{
			sessionNew = getSessionFactory().openSession();
			Query query = sessionNew.createSQLQuery("UPDATE classification SET processed = 1, PROCESSING_DATE="+ "'" +date+ "'" +", FEED_STATUS="+status.ordinal()+", COMPANY="+ "'" + company+ "'" +", ERROR_DESCRIPTION="+ "'" +errorDescription+ "'" +" where processed=0");
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
