package com.musimundo.servicemonitor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;
import com.musimundo.servicemonitor.dao.ServiceCheckDao;
import com.musimundo.servicemonitor.dao.ServiceCheckerDao;
import com.musimundo.servicemonitor.dao.ServiceToCheckDao;


@Service("serviceChecker")
@Transactional
public class ServiceCheckerImpl implements ServiceChecker{
	
	@Autowired
	ServiceCheckerDao serviceCheckerDao;
	
	@Autowired
	ServiceCheckDao serviceCheckDao;
	
	@Autowired
	ServiceToCheckService serviceToCheckService;
	
	@Autowired
	static List<String> serviceToCheckList = new ArrayList<String>();

	@Override
	@Async
	@Transactional
	public ServiceCheck checkService(ServiceToCheck serviceToCheck) {
		ServiceCheck serviceCheck = null;
		if(!serviceToCheckList.contains(serviceToCheck.getName())) {
			serviceToCheckList.add(serviceToCheck.getName());
			serviceCheck = serviceCheckerDao.checkService(serviceToCheck);
			serviceCheckDao.save(serviceCheck);
			serviceToCheck.setLastCheckTime(serviceCheck.getCheckTime());
			if(!serviceCheck.isActive()) {
				//enviar alerta
				serviceToCheck.setState("DOWN");
			}else if(serviceCheck.isActive() && (serviceCheck.getElapsedTime()> Long.parseLong(serviceToCheck.getTimeOut())*1000)) {
				//enviar alerta
				serviceToCheck.setState("LATE");
			}else {
				serviceToCheck.setState("OK");
			}
			serviceToCheck.setElapsedTime(serviceCheck.getElapsedString());
			serviceToCheckService.updateStateLastCheckServiceToCheck(serviceToCheck);
			System.out.println("Servicio Chequeado: "+serviceToCheck.getName()+"ServiceCheck creado: id: "+serviceCheck.getId());
			System.out.println("Name: "+serviceCheck.getName());
			System.out.println("URL: "+serviceCheck.getUrl());
			System.out.println("Elapsed Time: "+serviceCheck.getElapsedTime());
			System.out.println("is Active: "+serviceCheck.isActive());
			serviceToCheckList.remove(serviceToCheck.getName());
		}
		
		return serviceCheck;
	}

	@Override
	public ServiceCheck findById(int id) {
		
		return serviceCheckDao.findById(id);
	}

	@Override
	public ServiceCheck findByName(String name) {
		
		return serviceCheckDao.findByName(name);
	}

	@Override
	public void save(ServiceCheck serviceCheck) {
		serviceCheckDao.save(serviceCheck);		
	}

	@Override
	public ServiceCheck findLastByIdService(int serviceId) {
		
		return serviceCheckDao.findLastByIdService(serviceId);
	}

	@Override
	public List<ServiceCheck> findAllServicesByIdService(ServiceToCheck service) {
		
		return serviceCheckDao.findAllServicesByIdService(service);
	}
	
	@Override
	public List<ServiceCheck> findAllServicesCheck() {
		
		return serviceCheckDao.findAllServicesCheck();
	}

}
