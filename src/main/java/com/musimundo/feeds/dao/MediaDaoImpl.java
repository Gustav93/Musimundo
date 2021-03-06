package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Media;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Utils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("mediaDao")
public class MediaDaoImpl extends AbstractDao <Integer, Media> implements MediaDao
{
    @Override
    public Media findById(int id) {
        Media res = getByKey(id);

        return res;
    }

    @Override
    public List<Media> findByProductCode(String productCode) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("productCode", productCode));
        List<Media> res = crit.list();

        return res;
    }

    @Override
    public List<Media> findAll() {
        Criteria crit = createEntityCriteria();
        List<Media> res = crit.list();

        return res;
    }

    @Override
    public List<Media> findNotProcessed() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("processed", false));
        return criteria.list();
    }

    @Override
    public void save(Media media) {
        persist(media);
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
	public List<Media> findMediaByDate(Date desde, Date hasta) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.between("processingDate", desde, hasta));

        List<Media> res = (List<Media>) criteria.list();

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
	public boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company) {
		Session sessionNew = null;
		String date = Utils.getDateString(new Date());
		try{
			sessionNew = getSessionFactory().openSession();
			Query query = sessionNew.createSQLQuery("UPDATE media SET processed = 1, PROCESSING_DATE="+ "'" +date+ "'" +", FEED_STATUS="+status.ordinal()+", COMPANY="+ "'" + company+ "'" +", ERROR_DESCRIPTION="+ "'" +errorDescription+ "'" +" where processed=0");
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
    public boolean insertMedialist(String insert) {
    	
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
}
