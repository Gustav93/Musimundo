package com.musimundo.carritos.dao;

import java.util.Calendar;

import com.musimundo.carritos.beans.ListaCarrosCerrados;

public interface ReporteCarrosDao {
	
	ListaCarrosCerrados getAllCarrosByDate(Calendar date);
	
	ListaCarrosCerrados getAllCarrosByPerdiod(String startDate, String endDate);
	
}
