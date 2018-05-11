package com.musimundo.carritos.service;

import java.util.Calendar;

import com.musimundo.carritos.beans.ListaCarrosCerrados;

public interface ReporteCarrosService {
	
	ListaCarrosCerrados getAllCarrosByDate(Calendar date);
	
	ListaCarrosCerrados getAllCarrosByPerdiod(String fechaDesde, String fechaHasta);
}
