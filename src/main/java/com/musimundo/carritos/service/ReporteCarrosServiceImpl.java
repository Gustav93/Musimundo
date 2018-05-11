package com.musimundo.carritos.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musimundo.carritos.beans.ListaCarrosCerrados;
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

}
