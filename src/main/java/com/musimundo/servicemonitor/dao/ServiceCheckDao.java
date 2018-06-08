package com.musimundo.servicemonitor.dao;

import java.util.List;

import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;

public interface ServiceCheckDao {
	
	ServiceCheck findById(int id);
	
	ServiceCheck findByName(String name);
	
	void save(ServiceCheck serviceCheck);
	
	ServiceCheck findLastByIdService(int serviceId);
	
	List<ServiceCheck> findAllServicesByIdService(ServiceToCheck service);

	List<ServiceCheck> findAllServicesCheck();

}
