package com.musimundo.carritos.service;


import com.musimundo.carritos.beans.CarroCerrado;
import com.musimundo.carritos.beans.ListaCarrosCerrados;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReporteCarros {
    private static ListaCarrosCerrados closedOrders;
    private static Integer totalCarros = 0;
    private static Integer totalCarrosCarsa = 0;
    private static Integer totalCarrosEmsa = 0;
    private static Integer totalProductos = 0;
    private static Integer totalProductosCarsa = 0;
    private static Integer totalProductosEmsa = 0;
    private static Double totalRecaudado = 0.0;
    private static Double totalRecaudadoCarsa = 0.0;
    private static Double totalRecaudadoEmsa = 0.0;
    private static Calendar fecha;

    public ReporteCarros()
    {
        this.fecha = Calendar.getInstance();
        this.closedOrders = consultarCarrosCerradosHoy();
    }

    //Recorre los carros cerrados y aprobados y cuenta la cantidad de productos, recaudado y carros total y de cada
    // empresa.
    public void setVariablesReporte() {
        this.totalCarros = 0;
        this.totalCarrosCarsa = 0;
        this.totalCarrosEmsa = 0;
        this.totalProductos = 0;
        this.totalProductosCarsa = 0;
        this.totalProductosEmsa = 0;
        this.totalRecaudado = 0.0;
        this.totalRecaudadoCarsa = 0.0;
        this.totalRecaudadoEmsa = 0.0;

        List<CarroCerrado> listaCarrosCerrados = getCarrosCerradosAprobados();

        for (CarroCerrado carro : listaCarrosCerrados)
        {
            if (carro.getEmpresa().equals("CARSA") && fechaCierreValida(carro))
                sumarCarro(carro);

            else if (carro.getEmpresa().equals("EMSA") && fechaCierreValida(carro))
                sumarCarro(carro);
        }
    }

    //devuelve una lista de carros que estan aprobados y cerrados en la fecha de hoy
    public static List<CarroCerrado> getCarrosCerradosAprobados() {

        List<CarroCerrado> res = new ArrayList();

        if (closedOrders == null)
            return res;

        for (CarroCerrado carro : closedOrders.getClosedOrders()) {
            if (carro.getEstadoPago().toUpperCase().equals("APPROVED") && fechaCierreValida(carro))
                res.add(carro);
        }

        return res;
    }

    public static Integer getTotalCarros() {
        return totalCarros;
    }

    public static Integer getTotalCarrosCarsa() {
        return totalCarrosCarsa;
    }

    public static Integer getTotalCarrosEmsa() {
        return totalCarrosEmsa;
    }

    public static Integer getTotalProductos() {
        return totalProductos;
    }

    public static Integer getTotalProductosCarsa() {
        return totalProductosCarsa;
    }

    public static Integer getTotalProductosEmsa() {
        return totalProductosEmsa;
    }

    public static Double getTotalRecaudado() {
        return totalRecaudado;
    }

    public static Double getTotalRecaudadoCarsa() {
        return totalRecaudadoCarsa;
    }

    public static Double getTotalRecaudadoEmsa() {
        return totalRecaudadoEmsa;
    }

    public static CarroCerrado getCarro(String id) {
        CarroCerrado res = null;
        if (id == null)
            throw new IllegalArgumentException("id invalido");

        for (CarroCerrado carro : closedOrders.getClosedOrders()) {
            if (carro.getId().equals(id)) {
                res = new CarroCerrado();
                res.setId(carro.getId());
                res.setFechaCreacionCarro(carro.getFechaCreacionCarro());
                res.setFechaCierrePedido(carro.getFechaCierrePedido());
                res.setFechaModificacion(carro.getFechaModificacion());
                res.setTotal(carro.getTotal());
                res.setEmailUsuario(carro.getEmailUsuario());
                res.setTelefono(carro.getTelefono());
                res.setDni(carro.getDni());
                res.setIp(carro.getIp());
                res.setProvincia(carro.getProvincia());
                res.setCodigoPostal(carro.getCodigoPostal());
                res.setCiudad(carro.getCiudad());
                res.setUsuarioInvitado(carro.getUsuarioInvitado());
                res.setEmpresaInicio(carro.getEmpresaInicio());
                res.setEmpresaFinal(carro.getEmpresaFinal());
                res.setMotivoDerivacion(carro.getMotivoDerivacion());
                res.setOrigenStockCarsa(carro.getOrigenStockCarsa());
                res.setOrigenStockEmsa(carro.getOrigenStockEmsa());
                res.setEstadoPedido(carro.getEstadoPedido());
                res.setEstadoPago(carro.getEstadoPago());
                res.setBanco(carro.getBanco());
                res.setTarjeta(carro.getTarjeta());
                res.setCuotas(carro.getCuotas());
                res.setPromocion(carro.getPromocion());
                res.setEsRetiroEnSucursal(carro.getEsRetiroEnSucursal());
                res.setCodigoSucursalRetiro(carro.getCodigoSucursalRetiro());
                res.setConTasaSubsidiada(carro.getConTasaSubsidiada());
                res.setMedioPago(carro.getMedioPago());
                res.setFechaAlta(carro.getFechaAlta());
                res.setTasaSubsidiada(carro.getTasaSubsidiada());
                res.setModoEntrega(carro.getModoEntrega());
                res.setEmpresa(carro.getEmpresa());
                res.setOrderEntries(carro.getOrderEntries());
                res.setTasaSubsidiada(carro.getTasaSubsidiada());
                break;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "{" +
                " totalCarros=" + totalCarros +
                ", totalCarrosCarsa=" + totalCarrosCarsa +
                ", totalCarrosEmsa=" + totalCarrosEmsa +
                ", totalProductos=" + totalProductos +
                ", totalProductosCarsa=" + totalProductosCarsa +
                ", totalProductosEmsa=" + totalProductosEmsa +
                ", totalRecaudado=" + String.format("%.2f", totalRecaudado) +
                ", totalRecaudadoCarsa=" + String.format("%.2f", totalRecaudadoCarsa) +
                ", totalRecaudadoEmsa=" + String.format("%.2f", totalRecaudadoEmsa) +
                '}';
    }

    //Consume el webservice y devuelve una lista con los carros modificados hoy
    // (puede devolver carros cerrados dias anteriores y fueron modificacos hoy)
    private ListaCarrosCerrados consultarCarrosCerradosHoy() {
        ListaCarrosCerrados listaCarrosCerrados;
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = dateFormat.format(this.fecha.getTime());

        String url = "https://yprd.musimundo.com/restws/v2/musimundo/orders/list/timeinterval?ordersDate=" + fecha;
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
        listaCarrosCerrados = response.getEntity(ListaCarrosCerrados.class);
        System.out.println(listaCarrosCerrados);

        return listaCarrosCerrados;
    }

    //suma a las variables estaticas la cantidad de productos y el precio de cada carro
    //( suma en total y por cada empresa)
    private void sumarCarro(CarroCerrado carro)
    {
        totalCarros++;
        totalRecaudado += carro.getTotal();
        totalProductos += carro.getCantProductosVendidos();

        if (carro.getEmpresa().equals("CARSA")) {
            totalCarrosCarsa++;
            totalRecaudadoCarsa += carro.getTotal();
            totalProductosCarsa += carro.getCantProductosVendidos();
        } else if (carro.getEmpresa().equals("EMSA")) {
            totalCarrosEmsa++;
            totalRecaudadoEmsa += carro.getTotal();
            totalProductosEmsa += carro.getCantProductosVendidos();
        }
    }

    //vefirica que la fecha de cierre del carro coincida con la fecha de hoy.
    private static boolean fechaCierreValida(CarroCerrado carro)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaActual = dateFormat.format(fecha.getTime());

        return carro.getFechaCierrePedido().contains(fechaActual);
    }
}