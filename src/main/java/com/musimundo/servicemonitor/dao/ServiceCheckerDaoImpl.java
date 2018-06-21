package com.musimundo.servicemonitor.dao;

import java.util.Date;
import java.util.concurrent.TimeUnit;


import org.springframework.stereotype.Repository;

import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;
import com.musimundo.servicemonitor.services.ServiceChecker;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Repository("serviceCheckerDao")
public class ServiceCheckerDaoImpl implements ServiceCheckerDao{

	@Override
	public ServiceCheck checkService(ServiceToCheck serviceToCheck) {
		
		ServiceCheck serviceCheck = new ServiceCheck();
		
		serviceCheck.setName(serviceToCheck.getName());
		serviceCheck.setCheckTime(new Date());
		serviceCheck.setUrl(serviceToCheck.getUrl());
		serviceCheck.setService(serviceToCheck);

		ClientConfig clientConfig = new DefaultClientConfig();
        //clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        String url = serviceToCheck.getUrl();
        WebResource webResource = client.resource(url);        
        //start
        long lStartTime = System.nanoTime();
        
        ClientResponse response = webResource.type(serviceToCheck.getType()).get(ClientResponse.class);
        
        //end
        long lEndTime = System.nanoTime();
        //time elapsed
        long output = lEndTime - lStartTime;
        
        long elapsed = output / 1000000;
        
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(elapsed),
                TimeUnit.MILLISECONDS.toMinutes(elapsed) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(elapsed)),
                TimeUnit.MILLISECONDS.toSeconds(elapsed) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsed)));
        
        serviceCheck.setElapsedString(hms);
        serviceCheck.setElapsedTime(elapsed);
        int code = response.getStatus();
         if(code == 200) {
        	 serviceCheck.setActive(true);
         }else {
        	 serviceCheck.setActive(false);
         }
         
         serviceCheck.setCodeResponse(Integer.toString(code));
		 
		return serviceCheck;
	}

}
