package com.musimundo.carritos.beans;

public class Articulo
{
    private String musicode, emsaCode, carsaCode, tituloProducto, categoriaPrincipal, categoriaPrincipalPadre;
    private Double precioArticulo, precioTotal;
    private Integer cantidadVendida;
    private boolean esProducto, esArticuloSubsidiado;

    public Articulo()
    {
        this.musicode = "";
        this.emsaCode = "";
        this.carsaCode = "";
        this.tituloProducto = "";
        this.categoriaPrincipal = "";
        this.categoriaPrincipalPadre = "";
        this.precioArticulo = 0.0;
        this.cantidadVendida = 0;
        this.precioTotal = 0.0;
        this.esProducto = false;
    }

    public String getMusicode() {
        return musicode;
    }

    public void setMusicode(String musicode) {
        this.musicode = musicode;
    }

    public String getCarsaCode() {
        return carsaCode;
    }

    public void setCarsaCode(String carsaCode) {
        this.carsaCode = carsaCode;
    }

    public String getTituloProducto() {
        return tituloProducto;
    }

    public void setTituloProducto(String tituloProducto) {
        this.tituloProducto = tituloProducto;
    }

    public String getCategoriaPrincipal() {
        return categoriaPrincipal;
    }

    public void setCategoriaPrincipal(String categoriaPrincipal) {
        this.categoriaPrincipal = categoriaPrincipal;
    }

    public String getCategoriaPrincipalPadre() {
        return categoriaPrincipalPadre;
    }

    public void setCategoriaPrincipalPadre(String categoriaPrincipalPadre) {
        this.categoriaPrincipalPadre = categoriaPrincipalPadre;
    }

    public Double getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(Double precioArticulo) {
        this.precioArticulo = precioArticulo;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public boolean getEsProducto() {
        return esProducto;
    }

    public void setEsProducto(boolean esProducto) {
        this.esProducto = esProducto;
    }

    public String getEmsaCode() {
        return emsaCode;
    }

    public void setEmsaCode(String emsaCode) {
        this.emsaCode = emsaCode;
    }

    public boolean esArticuloSubsidiado() {
        return esArticuloSubsidiado;
    }

    public void setEsArticuloSubsidiado(boolean esArticuloSubsidiado) {
        this.esArticuloSubsidiado = esArticuloSubsidiado;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "musicode='" + musicode + '\'' +
                ", carsaCode='" + carsaCode + '\'' +
                ", tituloProducto='" + tituloProducto + '\'' +
                ", categoriaPrincipal='" + categoriaPrincipal + '\'' +
                ", categoriaPrincipalPadre='" + categoriaPrincipalPadre + '\'' +
                ", precioArticulo=" + precioArticulo +
                ", precioTotal=" + precioTotal +
                ", cantidadVendida=" + cantidadVendida +
                ", getEsProducto=" + esProducto +
                '}';
    }
}