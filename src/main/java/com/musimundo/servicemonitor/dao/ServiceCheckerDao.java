package com.musimundo.servicemonitor.dao;

import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;

public interface ServiceCheckerDao {
	
	ServiceCheck checkService(ServiceToCheck serviceToCheck);

}
