package com.musimundo.carritos.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CarroCerrado
{
    private String id, fechaCreacionCarro, fechaCierrePedido, fechaModificacion, emailUsuario, telefono, dni, ip,
    provincia, codigoPostal, ciudad, empresaInicio, empresaFinal, motivoDerivacion, origenStockCarsa, origenStockEmsa,
    estadoPedido, estadoPago, banco, tarjeta, cuotas, promocion, codigoSucursalRetiro, nombreSucursalRetiro, medioPago,
    fechaAlta, tasaSubsidiada, modoEntrega, empresa;
    private Double total;
    private boolean usuarioInvitado, esRetiroEnSucursal, conTasaSubsidiada;
    private ListaArticulos orderEntries;

    public CarroCerrado() {
        this.id = "";
        this.fechaCreacionCarro = "";
        this.fechaCierrePedido = "";
        this.fechaModificacion = "";
        this.total = 0.0;
        this.emailUsuario = "";
        this.telefono = "";
        this.dni = "";
        this.ip = "";
        this.provincia = "";
        this.codigoPostal = "";
        this.ciudad = "";
        this.empresaInicio = "";
        this.empresaFinal = "";
        this.motivoDerivacion = "";
        this.origenStockCarsa = "";
        this.origenStockEmsa = "";
        this.estadoPedido = "";
        this.estadoPago = "";
        this.banco = "";
        this.tarjeta = "";
        this.cuotas = "";
        this.promocion = "";
        this.codigoSucursalRetiro = "";
        this.nombreSucursalRetiro = "";
        this.medioPago = "";
        this.fechaAlta = "";
        this.tasaSubsidiada = "";
        this.modoEntrega = "";
        this.empresa = "";
        this.usuarioInvitado = false;
        this.esRetiroEnSucursal = false;
        this.conTasaSubsidiada = false;
        this.orderEntries = new ListaArticulos();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaCreacionCarro() {
        return fechaCreacionCarro;
    }

    public void setFechaCreacionCarro(String fechaCreacionCarro) {
        this.fechaCreacionCarro = fechaCreacionCarro;
    }


//    public String getFechaCierrePedido() {
//        String fecha;
//        if(fechaCierrePedido == null)
//        {
//            fecha = "";
//            return fecha;
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//        fecha = dateFormat.format(fechaCierrePedido.getTime());
//
//        return fecha;
//    }
//
//    public void setFechaCierrePedido(String fechaCierrePedido) {
//        this.fechaCierrePedido = Calendar.getInstance();
//        Date fecha = null;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        if(fechaCierrePedido.equals(""))
//        {
//            this.fechaCierrePedido = null;
//            return;
//        }
//
//        try {
//            fecha = dateFormat.parse(fechaCierrePedido);
//        } catch (ParseException e) {
//
//            e.printStackTrace();
//        }
//
//
//        System.out.println(fecha);
//        this.fechaCierrePedido.setTime(fecha);
//    }


    public String getFechaCierrePedido() {
        return fechaCierrePedido;
    }

    public void setFechaCierrePedido(String fechaCierrePedido) {
        this.fechaCierrePedido = fechaCierrePedido;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmpresaInicio() {
        return empresaInicio;
    }

    public void setEmpresaInicio(String empresaInicio) {
        this.empresaInicio = empresaInicio;
    }

    public String getEmpresaFinal() {
        return empresaFinal;
    }

    public void setEmpresaFinal(String empresaFinal) {
        this.empresaFinal = empresaFinal;
    }

    public String getMotivoDerivacion() {
        return motivoDerivacion;
    }

    public void setMotivoDerivacion(String motivoDerivacion) {
        this.motivoDerivacion = motivoDerivacion;
    }

    public String getOrigenStockCarsa() {
        return origenStockCarsa;
    }

    public void setOrigenStockCarsa(String origenStockCarsa) {
        this.origenStockCarsa = origenStockCarsa;
    }

    public String getOrigenStockEmsa() {
        return origenStockEmsa;
    }

    public void setOrigenStockEmsa(String origenStockEmsa) {
        this.origenStockEmsa = origenStockEmsa;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getCuotas() {
        return cuotas;
    }

    public void setCuotas(String cuotas) {
        this.cuotas = cuotas;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String getCodigoSucursalRetiro() {
        return codigoSucursalRetiro;
    }

    public void setCodigoSucursalRetiro(String codigoSucursalRetiro) {
        this.codigoSucursalRetiro = codigoSucursalRetiro;
    }

    public String getNombreSucursalRetiro() {
        return nombreSucursalRetiro;
    }

    public void setNombreSucursalRetiro(String nombreSucursalRetiro) {
        this.nombreSucursalRetiro = nombreSucursalRetiro;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getTasaSubsidiada() {
        return tasaSubsidiada;
    }

    public void setTasaSubsidiada(String tasaSubsidiada) {
        this.tasaSubsidiada = tasaSubsidiada;
    }

    public String getModoEntrega() {
        return modoEntrega;
    }

    public void setModoEntrega(String modoEntrega) {
        this.modoEntrega = modoEntrega;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public boolean getUsuarioInvitado() {
        return usuarioInvitado;
    }

    public void setUsuarioInvitado(boolean usuarioInvitado) {
        this.usuarioInvitado = usuarioInvitado;
    }

    public boolean getEsRetiroEnSucursal() {
        return esRetiroEnSucursal;
    }

    public void setEsRetiroEnSucursal(boolean esRetiroEnSucursal) {
        this.esRetiroEnSucursal = esRetiroEnSucursal;
    }

    public boolean getConTasaSubsidiada() {
        return conTasaSubsidiada;
    }

    public void setConTasaSubsidiada(boolean conTasaSubsidiada) {
        this.conTasaSubsidiada = conTasaSubsidiada;
    }

    public ListaArticulos getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(ListaArticulos orderEntries) {
        this.orderEntries = orderEntries;
    }

    public Integer getCantProductosVendidos()
    {
        Integer cantProductos = 0;

        List<Articulo> entriesList = orderEntries.getEntries();

        for(Articulo articulo : entriesList)
        {
            if(articulo.getEsProducto())
                cantProductos+= articulo.getCantidadVendida();
        }

        return cantProductos;
    }

    @Override
    public String toString() {
        return "ClosedOrder{" +
                "id='" + id + '\'' +
                ", fechaCreacionCarro='" + fechaCreacionCarro + '\'' +
                ", fechaCierrePedido='" + fechaCierrePedido + '\'' +
                ", fechaModificacion='" + fechaModificacion + '\'' +
                ", total='" + total + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dni='" + dni + '\'' +
                ", ip='" + ip + '\'' +
                ", provincia='" + provincia + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", empresaInicio='" + empresaInicio + '\'' +
                ", empresaFinal='" + empresaFinal + '\'' +
                ", motivoDerivacion='" + motivoDerivacion + '\'' +
                ", origenStockCarsa='" + origenStockCarsa + '\'' +
                ", origenStockEmsa='" + origenStockEmsa + '\'' +
                ", estadoPedido='" + estadoPedido + '\'' +
                ", estadoPago='" + estadoPago + '\'' +
                ", banco='" + banco + '\'' +
                ", tarjeta='" + tarjeta + '\'' +
                ", cuotas='" + cuotas + '\'' +
                ", promocion='" + promocion + '\'' +
                ", codigoSucursalRetiro='" + codigoSucursalRetiro + '\'' +
                ", nombreSucursalRetiro='" + nombreSucursalRetiro + '\'' +
                ", medioPago='" + medioPago + '\'' +
                ", fechaAlta='" + fechaAlta + '\'' +
                ", tasaSubsidiada='" + tasaSubsidiada + '\'' +
                ", modoEntrega='" + modoEntrega + '\'' +
                ", empresa='" + empresa + '\'' +
                ", usuarioInvitado=" + usuarioInvitado +
                ", esRetiroEnSucursal=" + esRetiroEnSucursal +
                ", conTasaSubsidiada=" + conTasaSubsidiada +
                ", orderEntries=" + orderEntries +
                '}';
    }
}
