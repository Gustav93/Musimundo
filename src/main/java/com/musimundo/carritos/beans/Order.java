package com.musimundo.carritos.beans;

public class Order
{
    private String id, emailUsuario, provincia, ciudad, estadoPedido, estadoPago, cuotas, promocion, medioPago, fechaAlta, tasaSubsidiada, modoEntrega, empresa;
    private Double total;
    private Boolean usuarioInvitado;
    private ListaArticulos orderEntries;

    public Order()
    {
        this.id = "";
        this.emailUsuario = "";
        this.provincia = "";
        this.ciudad = "";
        this.estadoPedido = "";
        this.estadoPago = "";
        this.cuotas = "";
        this.promocion = "";
        this.medioPago = "";
        this.fechaAlta = "";
        this.tasaSubsidiada = "";
        this.modoEntrega = "";
        this.empresa = "";
        this.total = 0.0;
        this.usuarioInvitado = false;
        this.orderEntries = new ListaArticulos();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getUsuarioInvitado() {
        return usuarioInvitado;
    }

    public void setUsuarioInvitado(Boolean usuarioInvitado) {
        this.usuarioInvitado = usuarioInvitado;
    }

    public ListaArticulos getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(ListaArticulos orderEntries) {
        this.orderEntries = orderEntries;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", provincia='" + provincia + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estadoPedido='" + estadoPedido + '\'' +
                ", estadoPago='" + estadoPago + '\'' +
                ", cuotas='" + cuotas + '\'' +
                ", promocion='" + promocion + '\'' +
                ", medioPago='" + medioPago + '\'' +
                ", fechaAlta='" + fechaAlta + '\'' +
                ", tasaSubsidiada='" + tasaSubsidiada + '\'' +
                ", modoEntrega='" + modoEntrega + '\'' +
                ", empresa='" + empresa + '\'' +
                ", total=" + total +
                ", usuarioInvitado=" + usuarioInvitado +
                ", orderEntries=" + orderEntries +
                '}';
    }
}
