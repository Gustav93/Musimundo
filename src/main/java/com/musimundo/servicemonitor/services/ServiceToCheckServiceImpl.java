package com.musimundo.servicemonitor.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musimundo.servicemonitor.beans.ServiceToCheck;
import com.musimundo.servicemonitor.dao.ServiceToCheckDao;

@Service("serviceToCheckService")
@Transactional
public class ServiceToCheckServiceImpl implements ServiceToCheckService{

	@Autowired
	private ServiceToCheckDao dao;
	
	@Override
	public ServiceToCheck findById(int id) {
		return dao.findById(id);
	}

	@Override
	public ServiceToCheck findByUrl(String url) {
		ServiceToCheck serviceToCheck = dao.findByUrl(url);
		return serviceToCheck;
	}

	@Override
	public void saveServiceToCheck(ServiceToCheck serviceToCheck) {
		Date today = new Date();
		serviceToCheck.setCreateTime(today);
		dao.save(serviceToCheck);		
	}

	@Override
	public void updateServiceToCheck(ServiceToCheck serviceToCheck) {
		ServiceToCheck entity = dao.findById(serviceToCheck.getId());
		if(entity!=null){
			entity.setName(serviceToCheck.getName());
			if(!serviceToCheck.getUrl().equals(entity.getUrl())){
				entity.setUrl(serviceToCheck.getUrl());
			}
			if(!serviceToCheck.getUser().equals(entity.getUser())){
				entity.setUser(serviceToCheck.getUser());
			}
			entity.setTimeOut(serviceToCheck.getTimeOut());
			entity.setType(serviceToCheck.getType());
		}
		
	}

	@Override
	public void deleteServiceToCheckByUrl(String url) {
		dao.deleteByUrl(url);		
	}

	@Override
	public List<ServiceToCheck> findAllServiceToCheck() {
		return dao.findAllServices();
	}

	@Override
	public boolean isServiceToCheckURLUnique(Integer id, String url) {
		ServiceToCheck serviceToCheck = findByUrl(url);
		return ( serviceToCheck == null || ((id != null) && (serviceToCheck.getId() == id)));
	}
	
	public void updateStateLastCheckServiceToCheck(ServiceToCheck service) {
		ServiceToCheck entity = dao.findById(service.getId());
		if(entity!=null){
			entity.setLastCheckTime(service.getLastCheckTime());
			entity.setState(service.getState());
			entity.setElapsedTime(service.getElapsedTime());
		}
	}

}
