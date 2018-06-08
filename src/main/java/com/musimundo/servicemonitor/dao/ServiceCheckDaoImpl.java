package com.musimundo.servicemonitor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import com.musimundo.dao.AbstractDao;
import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;

@Repository("serviceCheckDao")
public class ServiceCheckDaoImpl extends AbstractDao<Integer, ServiceCheck> implements ServiceCheckDao{

	static final Logger logger = LoggerFactory.getLogger(ServiceCheckDaoImpl.class);
	
	@Override
	public ServiceCheck findById(int id) {
		ServiceCheck serviceCheck = getByKey(id);
		if(serviceCheck!=null){
			Hibernate.initialize(serviceCheck.getService());
		}
		return serviceCheck;
	}

	@Override
	public ServiceCheck findByName(String name) {
		logger.info("name : {}", name);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		ServiceCheck serviceCheck = (ServiceCheck)crit.uniqueResult();
		if(serviceCheck!=null){
			Hibernate.initialize(serviceCheck.getService());
		}
		return serviceCheck;
	}

	@Override
	public void save(ServiceCheck serviceCheck) {
		persist(serviceCheck);		
	}

	@Override
	public ServiceCheck findLastByIdService(int serviceId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("service_id", serviceId));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.max("CHECK_TIME"));
		crit.setProjection(projectionList);
		crit.setMaxResults(1);
		ServiceCheck serviceCheck = (ServiceCheck)crit.list();
		return serviceCheck;
	}

	@Override
	public List<ServiceCheck> findAllServicesByIdService(ServiceToCheck service) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.add(Restrictions.eq("service", service));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<ServiceCheck> serviceCheck = (List<ServiceCheck>) criteria.list();
		
		return serviceCheck;
	}

	@Override
	public List<ServiceCheck> findAllServicesCheck() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates
		criteria.setMaxResults(10);
		List<ServiceCheck> serviceCheck = (List<ServiceCheck>) criteria.list();
		
		return serviceCheck;
	}

}
