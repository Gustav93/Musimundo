package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Stock;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Utils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("stockDao")
public class StockDaoImpl extends AbstractDao <Integer, Stock> implements StockDao
{

    @Override
    public Stock findById(int id) {
        Stock res = getByKey(id);

        return res;
    }

    @Override
    public List<Stock> findByProductCode(String productCode) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCode", productCode));

        List<Stock> res = criteria.list();

        return res;
    }

    @Override
    public List<Stock> findAll() {
        Criteria criteria = createEntityCriteria();

        List<Stock> res = criteria.list();

        return res;
    }

    @Override
    public List<Stock> findNotProcessed() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("processed", false));
        return criteria.list();
    }

    @Override
    public void save(Stock stock) {
        persist(stock);
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
	public List<Stock> findStockByDate(Date desde, Date hasta) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.between("processingDate", desde, hasta));

        List<Stock> res = (List<Stock>) criteria.list();

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
    public void saveList(List<Stock> stockList) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            for(Stock stock : stockList)
                session.save(stock);

            transaction.commit();
        }
        catch (Exception e){
            System.out.println(e);
            transaction.rollback();
        }
        finally {
            session.close();
        }
    }
	
	@Override
	public boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company) {
		Session sessionNew = null;
        String date = Utils.getDateString(new Date());
		try{
			sessionNew = getSessionFactory().openSession();
            Query query = sessionNew.createSQLQuery("UPDATE stock SET processed = 1, PROCESSING_DATE="+ "'" +date+ "'" +", FEED_STATUS="+status.ordinal()+", COMPANY="+ "'" + company+ "'" +", ERROR_DESCRIPTION="+ "'" +errorDescription+ "'" +" where processed=0");
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
    public boolean insertStocklist(String insert) {
    	
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
