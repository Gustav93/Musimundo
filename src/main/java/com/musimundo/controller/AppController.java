package com.musimundo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.musimundo.feeds.beans.*;
import com.musimundo.feeds.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.musimundo.carritos.beans.CarroCerrado;
import com.musimundo.carritos.beans.ListaCarrosCerrados;
import com.musimundo.carritos.beans.StockJSON;
import com.musimundo.carritos.beans.TotalesCarritos;
import com.musimundo.carritos.service.ReporteCarrosService;
import com.musimundo.carritos.service.StockCarritosService;
import com.musimundo.model.User;
import com.musimundo.model.UserProfile;
import com.musimundo.service.UserProfileService;
import com.musimundo.service.UserService;
import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;
import com.musimundo.servicemonitor.services.ServiceChecker;
import com.musimundo.servicemonitor.services.ServiceToCheckService;




@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;
	
	@Autowired
	StockCarritosService stockService;
	
	@Autowired
	ServiceToCheckService servicesToCheckService;
	
	@Autowired
	ServiceChecker servicesChecker;
	
	@Autowired
	ReporteCarrosService reporteCarrosService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	static TotalesCarritos totalesCarritos = new TotalesCarritos();
	
	@Autowired
	static List<CarroCerrado> listaCarritos = new ArrayList<CarroCerrado>();
	
	@Autowired
	static List<ServiceToCheck> servicesToCheck = new ArrayList<ServiceToCheck>();
	
	@Autowired
	static List<ServiceCheck> serviceChecks = new ArrayList<ServiceCheck>();
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("liActivo", "liListUsuarios");
		model.addAttribute("loggedinuser", getPrincipal());
		return "userslist";
	}
	
	/**
	 * This method will list all existing service to check.
	 */
	@RequestMapping(value = {"/listservice" }, method = RequestMethod.GET)
	public String listServicesToCheck(ModelMap model) {

		List<ServiceToCheck> servicesToCheck = servicesToCheckService.findAllServiceToCheck();
		model.addAttribute("servicesToCheck", servicesToCheck);
		model.addAttribute("liActivo", "liListServicios");
		model.addAttribute("loggedinuser", getPrincipal());
		return "servicetochecklist";
	}
	
	/**
	 * This method will list all existing service to check.
	 */
	@RequestMapping(value = {"/servicesmonitor" }, method = RequestMethod.GET)
	public String servicesMonitor(ModelMap model) {				
		
		model.addAttribute("servicesToCheck", servicesToCheck);
		model.addAttribute("serviceChecks", serviceChecks);
		model.addAttribute("liActivo", "liMonitorServicios");
		model.addAttribute("loggedinuser", getPrincipal());
		return "servicemonitor";
	}
		
	/**
	 * This method will list all shop carts of the day.
	 */
	@RequestMapping(value = {"/indexcarritos" }, method = RequestMethod.GET)
	public String indexcarritos(ModelMap model) {		
		
		model.addAttribute("totalesCarritos", totalesCarritos);
		model.addAttribute("carritos", listaCarritos);
		model.addAttribute("liActivo", "liCarritos");
		model.addAttribute("loggedinuser", getPrincipal());
		return "carritosindex";
	}

		
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
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
		model.addAttribute("loggedinuser", getUser());
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
		model.addAttribute("loggedinuser", getPrincipal());
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
		model.addAttribute("loggedinuser", getPrincipal());
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
		model.addAttribute("loggedinuser", getUser());
		
		return "serviceshow";
	}	

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}		
		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "registrationsuccess";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}
	

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/bifurcacion"; 
	    	//return "redirect:/list";  
	    }
	}
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = {"/", "/bifurcacion" }, method = RequestMethod.GET)
	public String bifurcacion(ModelMap model) {

		/*List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);*/
		model.addAttribute("loggedinuser", getPrincipal());
		return "bifurcador";
	}
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = {"/carritos" }, method = RequestMethod.GET)
	public String carritos(ModelMap model) {

		/*List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);*/
		model.addAttribute("loggedinuser", getPrincipal());
		return "carritosindex";
	}
	
	/**
	 * This method will Stock.
	 */
	@RequestMapping(value = {"/consultastock" }, method = RequestMethod.GET)
	public String consultastock(ModelMap model) {
		
		
		/*List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);*/
		model.addAttribute("stockDisponible", "");
		model.addAttribute("cantStock", "");
		model.addAttribute("liActivo", "liConsultaStock");
		model.addAttribute("loggedinuser", getPrincipal());
		return "consultastockcarro";
	}
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = {"/consultarcarro" }, method = RequestMethod.GET)
	public String consultarcarro(ModelMap model) {

		/*List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);*/
		model.addAttribute("liActivo", "liCarritosPeriodo");
		model.addAttribute("loggedinuser", getPrincipal());
		return "consultacarro";
	}
	
	/**
	 * This method will list all shop cars of the day.
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/getcarritosperiodo" }, method = RequestMethod.GET)
	public String getcarritosperiodo(@RequestParam String fechaDesde, @RequestParam String fechaHasta, ModelMap model) throws Exception {
				        
		List<CarroCerrado> listaCarritos = new ArrayList<CarroCerrado>();
        TotalesCarritos totalesCarritos = new TotalesCarritos();
        
        //obtener carros aprobados y totales para mostrar.
		listaCarritos = reporteCarrosService.getCarrosAprobadosYTotalesPeriodo(fechaDesde, fechaHasta, totalesCarritos);
		
		model.addAttribute("totalesCarritos", totalesCarritos);
		model.addAttribute("carritos", listaCarritos);
		model.addAttribute("fechaDesde", fechaDesde);
		model.addAttribute("fechaHasta", fechaHasta);
		model.addAttribute("liActivo", "liCarritosPeriodo");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "carrosporperiodo";
	}
	
	/**
	 * This method will list all shop cars of the day.
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/getstock" }, method = RequestMethod.GET)
	public String getstock(@RequestParam String empresa, @RequestParam String codigo, ModelMap model) throws Exception {
		
		System.out.println(empresa);
		System.out.println(codigo);
		String stockDisponible = "";
		int cantStock = 0;
		
		if(empresa != null && codigo != null) {
			StockJSON stockJson = stockService.getStock(codigo, empresa);
			if(stockJson.getStockLevels().get(1).getInStockStatus().equals("forceInStock")) {
				stockDisponible = "Si";
			}else {
				stockDisponible = "No";
			}
			
			if(stockJson.getStockLevels().get(0).getAvailable() != null) {
				cantStock = stockJson.getStockLevels().get(0).getAvailable();
			}
			
		}
		
		model.addAttribute("stockDisponible", stockDisponible);
		model.addAttribute("cantStock", cantStock);
		model.addAttribute("liActivo", "liConsultaStock");
		model.addAttribute("loggedinuser", getPrincipal());
		return "consultastockcarro";
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
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
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
}