package com.musimundo.carritos.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musimundo.carritos.beans.CarroCerrado;
import com.musimundo.carritos.beans.ListaCarrosCerrados;
import com.musimundo.carritos.beans.TotalesCarritos;
import com.musimundo.carritos.dao.ReporteCarrosDao;

@Service("reporteCarrosService")
@Transactional
public class ReporteCarrosServiceImpl implements ReporteCarrosService{

	@Autowired
	private ReporteCarrosDao dao;
	
	@Override
	public ListaCarrosCerrados getAllCarrosByDate(Calendar date) {
		return dao.getAllCarrosByDate(date);
	}

	@Override
	public ListaCarrosCerrados getAllCarrosByPerdiod(String startDate, String endDate) {
		return dao.getAllCarrosByPerdiod(startDate, endDate);
	}
	
	@Override
	public List<CarroCerrado> getCarrosAprobadosYTotalesPeriodo(String startDate, String endDate, TotalesCarritos totalesCarritos){
		ListaCarrosCerrados listaCarrosCerrados = new ListaCarrosCerrados();
		List<CarroCerrado> listaCarritos = new ArrayList<CarroCerrado>();
		
		try {
			
			listaCarrosCerrados = dao.getAllCarrosByPerdiod(startDate, endDate);			
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar calDesde = Calendar.getInstance();
			Calendar calHasta = Calendar.getInstance();
			calDesde.setTime(dateFormat.parse(startDate));
			calHasta.setTime(dateFormat.parse(endDate));		
		
			listaCarritos = getCarrosAprobadosYTotalesPeriodo(calDesde.getTime(), calHasta.getTime(), listaCarrosCerrados, totalesCarritos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaCarritos;		
	}
	
	/**
	 * This method returns approved shop carts and totals.
	 * @throws Exception 
	 */
	private List<CarroCerrado> getCarrosAprobadosYTotalesPeriodo(Date fechaDesde, Date fechaHasta,
			ListaCarrosCerrados listaCarrosCerrados, TotalesCarritos totalesCarritos) throws Exception {
		List<CarroCerrado> res = new ArrayList<CarroCerrado>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar calUtil = Calendar.getInstance();
		if(listaCarrosCerrados != null) {
									
	        for (CarroCerrado carro : listaCarrosCerrados.getClosedOrders()) {
	        	
	        	if(carro.getFechaCierrePedido() != null && !carro.getFechaCierrePedido().isEmpty()) {
	        		calUtil.setTime(dateFormat.parse(carro.getFechaCierrePedido()));	        	
	        	
		        	if(calUtil.getTime().after(fechaDesde) && calUtil.getTime().before(fechaHasta)) {
			            if (carro.getEstadoPago().toUpperCase().equals("APPROVED")) {
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
	        }
		}
		return res;
	}

}
