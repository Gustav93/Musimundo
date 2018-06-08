package com.musimundo.servicemonitor.dao;

import java.util.List;

import com.musimundo.servicemonitor.beans.ServiceToCheck;
import com.musimundo.utilities.FeedType;

public interface ServiceToCheckDao {

	ServiceToCheck findById(int id);
	
	ServiceToCheck findByUrl(String url);
	
	void save(ServiceToCheck serviceToCheck);
	
	void deleteByUrl(String url);
	
	List<ServiceToCheck> findAllServices();
}
