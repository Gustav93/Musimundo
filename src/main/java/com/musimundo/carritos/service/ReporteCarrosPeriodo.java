package com.musimundo.carritos.service;


import com.musimundo.carritos.beans.CarroCerrado;
import com.musimundo.carritos.beans.ListaCarrosCerrados;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReporteCarrosPeriodo
{
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
    private static String fechaFinExtendida;
    private static Date fechaInicio, fechaFin;

    public ReporteCarrosPeriodo(String fechaInicio, String fechaFin)
    {
        this.fechaFinExtendida = agregarDia(fechaFin);
        this.closedOrders = consultarCarrosCerradosPeriodo(fechaInicio, fechaFin);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.fechaInicio = dateFormat.parse(fechaInicio);
            this.fechaFin = dateFormat.parse(fechaFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    //devuelve una lista de carros modificados entre las fechas dadas, para conseguir los carros cerrados del periodo
    // pasado como parametro, de trae los carros del dia posterior al periodo ya que puede existir el caso en que se
    // modifique un carro cerrado un dia despues y no se tenga en cuenta a la hora de traer los carros.
    private ListaCarrosCerrados consultarCarrosCerradosPeriodo(String fechaInicio, String fechaFin)
    {
        ListaCarrosCerrados listaCarrosCerrados = new ListaCarrosCerrados();

        if(fechaInicio == null || fechaFin == null)
            return listaCarrosCerrados;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
        Client client = Client.create(clientConfig);

        String url = "https://yprd.musimundo.com/restws/v2/musimundo/orders/list/timeinterval?startDate="+fechaInicio+"%2000:00:00&endDate="+fechaFinExtendida+"%2023:59:00";

        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
        listaCarrosCerrados = response.getEntity(ListaCarrosCerrados.class);
        System.out.println(listaCarrosCerrados);

        return listaCarrosCerrados;
    }

    public static List<CarroCerrado> getCarrosCerradosAprobados() {

        List<CarroCerrado> res = new ArrayList();

        for(CarroCerrado carro : closedOrders.getClosedOrders())
        {
            if (carro.getEstadoPago().equals("APPROVED") && fechaCirreValida(carro))
                res.add(carro);
        }

        return res;
    }

    public void setVariablesReporte()
    {
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

        for(CarroCerrado carro : listaCarrosCerrados)
        {
            if(carro.getEmpresa().equals("CARSA") && fechaCirreValida(carro))
                sumarCarro(carro);

            else if(carro.getEmpresa().equals("EMSA") && fechaCirreValida(carro))
                sumarCarro(carro);
        }
    }

    private void sumarCarro(CarroCerrado carro)
    {
        totalCarros++;
        totalRecaudado += carro.getTotal();
        totalProductos += carro.getCantProductosVendidos();

        if(carro.getEmpresa().equals("CARSA"))
        {
            totalCarrosCarsa++;
            totalRecaudadoCarsa += carro.getTotal();
            totalProductosCarsa += carro.getCantProductosVendidos();
        }

        else if(carro.getEmpresa().equals("EMSA"))
        {
            totalCarrosEmsa++;
            totalRecaudadoEmsa += carro.getTotal();
            totalProductosEmsa += carro.getCantProductosVendidos();
        }
    }

    private String agregarDia(String fecha)
    {
        if(fecha == null)
            throw new IllegalArgumentException();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date lafecha = null;
        try {
            lafecha = sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lafecha);
        calendar.add(Calendar.DATE, 1);

        String res = sdf.format(calendar.getTime());

        return res;
    }

    public static CarroCerrado getCarro(String id)
    {
        CarroCerrado res = null;
        if(id == null)
            throw new IllegalArgumentException("id invalido");

        for(CarroCerrado carro : closedOrders.getClosedOrders())
        {
            if(carro.getId().equals(id))
            {
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
        return "Reporte{" +
                " totalCarros=" + totalCarros +
                ", totalCarrosCarsa=" + totalCarrosCarsa +
                ", totalCarrosEmsa=" + totalCarrosEmsa +
                ", totalProductos=" + totalProductos +
                ", totalProductosCarsa=" + totalProductosCarsa +
                ", totalProductosEmsa=" + totalProductosEmsa +
                ", totalRecaudado=" + totalRecaudado +
                ", totalRecaudadoCarsa=" + totalRecaudadoCarsa +
                ", totalRecaudadoEmsa=" + totalRecaudadoEmsa +
                '}';
    }

    //chequea que la fecha de cierre del carro este entre la fecha de inicio y la fecha de fin del periodo.
    private static boolean fechaCirreValida(CarroCerrado carro)
    {
        if(carro.getFechaCierrePedido().equals(""))
            return false;

        Date fechaCarro = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            fechaCarro = dateFormat.parse(carro.getFechaCierrePedido());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (!fechaCarro.before(fechaInicio) && !fechaCarro.after(fechaFin));
    }
}
