package com.musimundo.servicemonitor.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;
import com.musimundo.servicemonitor.services.ServiceChecker;

public class JobTask implements Job{
	
	@Autowired
	ServiceChecker serviceChecker;

	private ServiceToCheck serviceToCkeck;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		ServiceCheck serviceCheck = serviceChecker.checkService(serviceToCkeck);		
		
	}

	public ServiceToCheck getServiceToCkeck() {
		return serviceToCkeck;
	}

	public void setServiceToCkeck(ServiceToCheck serviceToCkeck) {
		this.serviceToCkeck = serviceToCkeck;
	}
	
	
	
	

}
