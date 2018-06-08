package com.musimundo.servicemonitor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.musimundo.model.User;
import com.musimundo.service.UserService;
import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;
import com.musimundo.servicemonitor.services.ServiceChecker;
import com.musimundo.servicemonitor.services.ServiceToCheckService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class ServiceMonitorController {	
	
	@Autowired
	UserService userService;
	
	@Autowired
	ServiceToCheckService servicesToCheckService;
	
	@Autowired
	ServiceChecker servicesChecker;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private
	static List<ServiceToCheck> servicesToCheck = new ArrayList<ServiceToCheck>();
	
	@Autowired
	private
	static List<ServiceCheck> serviceChecks = new ArrayList<ServiceCheck>();
	
	/**
	 * This method will list all existing service to check.
	 */
	@RequestMapping(value = {"/servicesmonitor" }, method = RequestMethod.GET)
	public String servicesMonitor(ModelMap model) {				
		
		model.addAttribute("servicesToCheck", getServicesToCheck());
		model.addAttribute("serviceChecks", getServiceChecks());
		model.addAttribute("liActivo", "liMonitorServicios");
		//model.addAttribute("loggedinuser", getPrincipal());
		return "servicemonitor";
	}
	
	/**
	 * This method will list all existing service to check.
	 */
	@RequestMapping(value = {"/listservice" }, method = RequestMethod.GET)
	public String listServicesToCheck(ModelMap model) {

		List<ServiceToCheck> servicesToCheck = servicesToCheckService.findAllServiceToCheck();
		model.addAttribute("servicesToCheck", servicesToCheck);
		model.addAttribute("liActivo", "liListServicios");
		//model.addAttribute("loggedinuser", getPrincipal());
		return "servicetochecklist";
	}
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newservice" }, method = RequestMethod.GET)
	public String newservice(ModelMap model) {
		ServiceToCheck serviceToCheck = new ServiceToCheck();
		model.addAttribute("serviceToCheck", serviceToCheck);
		model.addAttribute("edit", false);
		model.addAttribute("liActivo", "liListServicios");
		//model.addAttribute("loggedinuser", getUser());
		return "serviceregistration";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/edit-service-{IdService}" }, method = RequestMethod.GET)
	public String editservice(@PathVariable int IdService, ModelMap model) {
		ServiceToCheck service = servicesToCheckService.findById(IdService);
		model.addAttribute("serviceToCheck", service);
		model.addAttribute("edit", true);
		model.addAttribute("liActivo", "");
		model.addAttribute("loggedinuser", getUser());
		return "serviceregistration";
	}
	
	@RequestMapping(value = { "/edit-service-{IdService}" }, method = RequestMethod.POST)
	public String updateservice(@Valid ServiceToCheck service, BindingResult result, ModelMap model) {
		
		if (result.hasErrors() && !result.getFieldError().getField().equals("user")) {
			return "serviceregistration";
		}
		
		service.setUser(getUser());
		
		servicesToCheckService.updateServiceToCheck(service);
		
		model.addAttribute("success", "service " + service.getName() + " "+ service.getUrl() + " registrado exitosamente");
		//model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/newservice" }, method = RequestMethod.POST)
	public String saveservice(@Valid ServiceToCheck service, BindingResult result, ModelMap model) {
		
		if (result.hasErrors() && !result.getFieldError().getField().equals("user")) {
			return "serviceregistration";
		}
		
		if(!servicesToCheckService.isServiceToCheckURLUnique(service.getId(), service.getUrl())) {
			FieldError ssoError =new FieldError("url","name",messageSource.getMessage("non.unique.ssoId", new String[]{service.getUrl()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "serviceregistration";
		}
		
		service.setUser(getUser());
		
		servicesToCheckService.saveServiceToCheck(service);
		
		model.addAttribute("success", "service " + service.getName() + " "+ service.getUrl() + " registrado exitosamente");
		//model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "registrationsuccess";
	}
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/show-service-{IdService}" }, method = RequestMethod.GET)
	public String showService(@PathVariable int IdService, ModelMap model) {
		ServiceToCheck service = servicesToCheckService.findById(IdService);
		List<ServiceCheck> listChecks = servicesChecker.findAllServicesByIdService(service);
		String classActive = "panel-success";
		if(service.getState().equals("DOWN")) {
			classActive = "panel-danger";
		}
		
		if(service.getState().equals("LATE")) {
			classActive = "panel-warning";
		}
		
		model.addAttribute("service", service);
		model.addAttribute("listChecks", listChecks);
		model.addAttribute("edit", false);
		model.addAttribute("classActive", classActive);
		model.addAttribute("liActivo", "liMonitorServicios");
		//model.addAttribute("loggedinuser", getUser());
		
		return "serviceshow";
	}
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private User getUser(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		User user = userService.findBySSO(userName);		
		
		return user;
	}

	public static List<ServiceToCheck> getServicesToCheck() {
		return servicesToCheck;
	}

	public static void setServicesToCheck(List<ServiceToCheck> servicesToCheck) {
		ServiceMonitorController.servicesToCheck = servicesToCheck;
	}

	public static List<ServiceCheck> getServiceChecks() {
		return serviceChecks;
	}

	public static void setServiceChecks(List<ServiceCheck> serviceChecks) {
		ServiceMonitorController.serviceChecks = serviceChecks;
	}

}
