package com.musimundo.servicemonitor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.musimundo.dao.AbstractDao;
import com.musimundo.servicemonitor.beans.ServiceToCheck;

@Repository("serviceToCheckDao")
public class ServiceToCheckDaoImpl extends AbstractDao<Integer, ServiceToCheck> implements ServiceToCheckDao{

	static final Logger logger = LoggerFactory.getLogger(ServiceToCheckDaoImpl.class);
	
	@Override
	public ServiceToCheck findById(int id) {
		ServiceToCheck serviceToCheck = getByKey(id);
		if(serviceToCheck!=null){
			Hibernate.initialize(serviceToCheck.getUser());
		}
		return serviceToCheck;
	}

	@Override
	public ServiceToCheck findByUrl(String url) {
		logger.info("url : {}", url);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("url", url));
		ServiceToCheck serviceToCheck = (ServiceToCheck)crit.uniqueResult();
		if(serviceToCheck!=null){
			Hibernate.initialize(serviceToCheck.getUser());
		}
		return serviceToCheck;
	}

	@Override
	public void save(ServiceToCheck serviceToCheck) {
		persist(serviceToCheck);		
	}

	@Override
	public void deleteByUrl(String url) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("url", url));
		ServiceToCheck serviceToCheck = (ServiceToCheck)crit.uniqueResult();
		delete(serviceToCheck);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceToCheck> findAllServices() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<ServiceToCheck> serviceToCheck = (List<ServiceToCheck>) criteria.list();
		
		return serviceToCheck;
	}

}
