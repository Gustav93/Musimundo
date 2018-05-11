package com.musimundo.servicemonitor.services;

import java.util.List;

import com.musimundo.servicemonitor.beans.ServiceToCheck;

public interface ServiceToCheckService {
	
	ServiceToCheck findById(int id);
	
	ServiceToCheck findByUrl(String url);
	
	void saveServiceToCheck(ServiceToCheck serviceToCheck);
	
	void updateServiceToCheck(ServiceToCheck serviceToCheck);
	
	void deleteServiceToCheckByUrl(String url);

	List<ServiceToCheck> findAllServiceToCheck(); 
	
	boolean isServiceToCheckURLUnique(Integer id, String url);
	
}
