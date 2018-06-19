package com.musimundo.carritos.dao;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.springframework.stereotype.Repository;

import com.musimundo.carritos.beans.StockJSON;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Repository("stockCarritosDao")
public class StockCarritosDaoImpl implements StockCarritosDao{

	@Override
	public StockJSON getStock(String json, String url) {
			StockJSON stockJson = null;
		 	Client client = Client.create();
	        WebResource resource = client.resource(url);
	        String response = resource.type(MediaType.APPLICATION_JSON).post(String.class, json);
	        
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        
	        try {
				stockJson = mapper.readValue(response, StockJSON.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		    return stockJson;
	}

}
