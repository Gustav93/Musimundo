package com.musimundo.feeds.dao;

import com.musimundo.feeds.beans.Product;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;
import com.musimundo.utilities.Utils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCode", productCode));

        List<Product> res = (List<Product>) criteria.list();

        return res;
    }

    @Override
    public List<Product> findAll() {
        Criteria criteria = createEntityCriteria();
        List<Product> res = (List<Product>) criteria.list();

        return res;
    }

    @Override
    public List<Product> findNotProcessed() {

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("processed", false));
        return criteria.list();
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
	public List<Product> findProductByDate(Date desde, Date hasta) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.between("processingDate", desde, hasta));

        List<Product> res = (List<Product>) criteria.list();

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
    public List<Product> findBy(Date desde, Date hasta, String empresa, Filter filter) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("company", empresa));

//        criteria.add(Restrictions.between("processingDate", desde, hasta));

        if(filter.equals(Filter.ONLY_NOT_OK))
        {
//            criteria.add(Restrictions.eq("feedStatus", FeedStatus.WARNING));
//            criteria.add(Restrictions.eq("feedStatus", FeedStatus.ERROR));
//            criteria.add(Restrictions.eq("feedStatus", FeedStatus.NOT_PROCESSED));

            Criterion criterion1 = Restrictions.and(Restrictions.eq("feedStatus", FeedStatus.WARNING));
            Criterion criterion2 = Restrictions.and(Restrictions.eq("feedStatus", FeedStatus.ERROR));
            Criterion criterion3 = Restrictions.and(Restrictions.eq("feedStatus", FeedStatus.NOT_PROCESSED));

            criteria.add(Restrictions.or(criterion1,criterion2,criterion3));
        }

//        criteria.setProjection(Projections.property("importOrigin"));
//        criteria.addOrder(Order.asc("importOrigin"));
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Product> res = criteria.list();

        return res;
    }
	
	@Override
	public boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company) {
		Session sessionNew = null;
		String date = Utils.getDateString(new Date());
		FeedStatus feedStatus = FeedStatus.OK;
		String description = "PRODUCT - La novedad se cargó exitosamente";
		try{
			sessionNew = getSessionFactory().openSession();
			Query query = sessionNew.createSQLQuery("UPDATE product SET processed = 1, PROCESSING_DATE="+ "'" +date+ "'" +", FEED_STATUS="+status.ordinal()+", COMPANY="+ "'" + company+ "'" +", ERROR_DESCRIPTION="+ "'" +errorDescription+ "'" +" where processed=0");
//            Query query = sessionNew.createQuery("UPDATE Product SET processed = 1, FEED_STATUS="+status.ordinal()+", COMPANY="+company+", ERROR_DESCRIPTION="+errorDescription+" where processed=0 and ID NOT IN("+notOk+")");
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
    public boolean insertProductlist(String insert) {
    	
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