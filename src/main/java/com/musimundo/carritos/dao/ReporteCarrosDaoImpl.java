package com.musimundo.carritos.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Repository;

import com.musimundo.carritos.beans.ListaCarrosCerrados;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Repository("reporteCarrosDao")
public class ReporteCarrosDaoImpl implements ReporteCarrosDao{

	@Override
	public ListaCarrosCerrados getAllCarrosByDate(Calendar date) {
		
		ListaCarrosCerrados listaCarrosCerrados;
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = dateFormat.format(date.getTime());

        String url = "https://yprd.musimundo.com/restws/v2/musimundo/orders/list/timeinterval?ordersDate=" + dateString;
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
        listaCarrosCerrados = response.getEntity(ListaCarrosCerrados.class);
        System.out.println("Carros Conseguidos");

        return listaCarrosCerrados;
	}

	@Override
	public ListaCarrosCerrados getAllCarrosByPerdiod(String startDate, String endDate) {
		ListaCarrosCerrados listaCarrosCerrados = new ListaCarrosCerrados();

        if(startDate == null || endDate == null)
            return listaCarrosCerrados;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
        Client client = Client.create(clientConfig);

        String url = "https://yprd.musimundo.com/restws/v2/musimundo/orders/list/timeinterval?startDate="+startDate+"%2000:00:00&endDate="+endDate+"%2023:59:00";

        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
        listaCarrosCerrados = response.getEntity(ListaCarrosCerrados.class);
        System.out.println("Carros Conseguidos");

        return listaCarrosCerrados;
	}

}
