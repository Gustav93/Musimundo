package com.musimundo.carritos.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musimundo.carritos.beans.StockJSON;
import com.musimundo.carritos.dao.ReporteCarrosDao;
import com.musimundo.carritos.dao.StockCarritosDao;

@Service("stockCarritosService")
@Transactional
public class StockCarritosServiceImpl implements StockCarritosService{
	
	@Autowired
	private StockCarritosDao dao;

	 private final String URL_CARSA = "http://200.45.42.55:8104/dev/api/v1/stock";
	 private final String URL_EMSA = "http://hybris.emusimundo.com/ystg/api/stock";
	
	@Override
	public StockJSON getStock(String codigo, String empresa) {
		
	    String codigoCarsa="";
	    String codigoEmsa ="";	   
	    String url="";
	    
	    
	    if(empresa.equals("Carsa"))
        {
            codigoCarsa = codigo;
            url = URL_CARSA;
        }
        else if(empresa.equals("Emsa"))
        {
            codigoEmsa = codigo;
            url = URL_EMSA;
        }

        //json que se envia al web service para preguntar por el stock de un producto
	    String json = "{\"cart\": { \"code\": \"2928475839292\", \"owner\": \"EMSA\", \"items\": [{\"codeCARSA\": \"" + codigoCarsa + "\",\"codeEMSA\": \"" + codigoEmsa + "\",\"quantity\": 1}]},\"timestamp\":\"2017-01-17T16:39:57-03:00\",\"provISO\": \"AR-B\"}";
	    
       
	    return dao.getStock(json, url);
	}

}
