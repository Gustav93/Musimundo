package com.musimundo.carritos.service;

import java.util.Calendar;
import java.util.List;

import com.musimundo.carritos.beans.CarroCerrado;
import com.musimundo.carritos.beans.ListaCarrosCerrados;
import com.musimundo.carritos.beans.TotalesCarritos;

public interface ReporteCarrosService {
	
	ListaCarrosCerrados getAllCarrosByDate(Calendar date);
	
	ListaCarrosCerrados getAllCarrosByPerdiod(String fechaDesde, String fechaHasta);

	List<CarroCerrado> getCarrosAprobadosYTotalesPeriodo(String startDate, String endDate,
			TotalesCarritos totalesCarritos);
}
