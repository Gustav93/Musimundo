package com.musimundo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musimundo.carritos.beans.CarroCerrado;
import com.musimundo.carritos.beans.ListaCarrosCerrados;
import com.musimundo.carritos.beans.TotalesCarritos;
import com.musimundo.carritos.service.ReporteCarrosService;
import com.musimundo.servicemonitor.beans.ServiceCheck;
import com.musimundo.servicemonitor.beans.ServiceToCheck;
import com.musimundo.servicemonitor.services.ServiceChecker;
import com.musimundo.servicemonitor.services.ServiceToCheckService;

@Controller
@RequestMapping("/")
public class CronController {
	
	@Autowired
	ReporteCarrosService reporteCarrosService;
	
	@Autowired
	ServiceChecker serviceChecker;
	
	@Autowired
	ServiceToCheckService serviceToCheckService;
	
	@Scheduled(cron = "0 0/10 * 1/1 * *")
	public void getcarritos() {
		System.out.println("Schedule ventas Start");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaActual = dateFormat.format(Calendar.getInstance().getTime());
        
	    //obtener carritos del servicio
		ListaCarrosCerrados listaCarrosCerrados = reporteCarrosService.getAllCarrosByDate(Calendar.getInstance());
		
		List<CarroCerrado> listaCarritos = new ArrayList<CarroCerrado>();
		TotalesCarritos totalesCarritos = new TotalesCarritos();      
		//obtener carros aprobados y totales para mostrar.
		listaCarritos = getCarrosAprobadosYTotales(fechaActual, listaCarrosCerrados, totalesCarritos);
		
		//reemplaza en Controller
		AppController.listaCarritos = listaCarritos;
		AppController.totalesCarritos = totalesCarritos;
		
		System.out.println("Carritos Actualizados Por Schedule");
	}
	
	@Scheduled(cron = "0 0/10 * 1/1 * *")
	public void servicecheck() {
		System.out.println("Schedule Service Start");
		
		List<ServiceToCheck> servicesToCheck = serviceToCheckService.findAllServiceToCheck();
        
		for(ServiceToCheck service:servicesToCheck) {			
			ServiceCheck serviceCheck = serviceChecker.checkService(service);			
		}	
		System.out.println("Servicios checkeados por schedule Por Schedule");
	}
	
	@Scheduled(cron = "0 0/2 * 1/1 * *")
	public void populateservicecheck() {
		System.out.println("Schedule Populate Service Start");
		
		List<ServiceToCheck> servicesToCheck = serviceToCheckService.findAllServiceToCheck();
		List<ServiceCheck> serviceChecks = serviceChecker.findAllServicesCheck();
        
		AppController.servicesToCheck = servicesToCheck;
		AppController.serviceChecks = serviceChecks;
		
		System.out.println("Servicios actualizados por schedule Por Populate Service");
	}
	
	/**
	 * This method returns approved shop carts and totals.
	 */
	private List<CarroCerrado> getCarrosAprobadosYTotales(String fechaActual,
			ListaCarrosCerrados listaCarrosCerrados, TotalesCarritos totalesCarritos) {
		List<CarroCerrado> res = new ArrayList<CarroCerrado>();
		if(listaCarrosCerrados != null) {
									
	        for (CarroCerrado carro : listaCarrosCerrados.getClosedOrders()) {
	            if (carro.getEstadoPago().toUpperCase().equals("APPROVED") && carro.getFechaCierrePedido().contains(fechaActual)) {
	            	if (carro.getEmpresa().equals("CARSA")||carro.getEmpresa().equals("EMSA")){
	    	        	totalesCarritos.setTotalCarros(totalesCarritos.getTotalCarros()+1);
	    	        	totalesCarritos.setTotalRecaudado(totalesCarritos.getTotalRecaudado()+ carro.getTotal());
	    	        	totalesCarritos.setTotalProductos(totalesCarritos.getTotalProductos()+carro.getCantProductosVendidos());

	    	            if (carro.getEmpresa().equals("CARSA")) {
	    	            	totalesCarritos.setTotalCarrosCarsa(totalesCarritos.getTotalCarrosCarsa()+1);
	    		        	totalesCarritos.setTotalRecaudadoCarsa(totalesCarritos.getTotalRecaudadoCarsa()+ carro.getTotal());
	    		        	totalesCarritos.setTotalProductosCarsa(totalesCarritos.getTotalProductosCarsa()+carro.getCantProductosVendidos());

	    	            } else if (carro.getEmpresa().equals("EMSA")) {
	    	            	totalesCarritos.setTotalCarrosEmsa(totalesCarritos.getTotalCarrosEmsa()+1);
	    		        	totalesCarritos.setTotalRecaudadoEmsa(totalesCarritos.getTotalRecaudadoEmsa()+ carro.getTotal());
	    		        	totalesCarritos.setTotalProductosEmsa(totalesCarritos.getTotalProductosEmsa()+carro.getCantProductosVendidos());
	    	            }
	    	        }	     
	            	res.add(carro);
	            }
	        }
		}
		return res;
	}

}
