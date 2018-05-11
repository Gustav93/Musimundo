package com.musimundo.feeds.beans.feeds;

import com.musimundo.utilities.EstadoProcesamiento;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRECIOS")
public class Precio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODIGO_PRODUCTO")
    private String codigoProducto;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "ONLINE_PRICE")
    private Double onlinePrice;

    @Column(name = "STORE_PRICE")
    private Double storePrice;

    @Column(name = "HAS_PRIORITY")
    private boolean hasPriority;

    @Column(name = "ORIGEN_IMPORTACION")
    private String origenImportacion;

    @Column(name = "FECHA_PROCESAMIENTO")
    private Date fechaProcesamiento;

    @Column(name = "ESTADO_PROCESAMIENTO")
    private EstadoProcesamiento estadoProcesamiento;

    @Column(name = "DESCRIPCION_ERROR")
    private String descripcionError;

    @Column(name = "EMPRESA")
    private String empresa;

    public Precio()
    {
        this.estadoProcesamiento = EstadoProcesamiento.NO_PROCESADO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(Double onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public Double getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(Double storePrice) {
        this.storePrice = storePrice;
    }

    public boolean isHasPriority() {
        return hasPriority;
    }

    public void setHasPriority(boolean hasPriority) {
        this.hasPriority = hasPriority;
    }

    public String getOrigenImportacion() {
        return origenImportacion;
    }

    public void setOrigenImportacion(String origenImportacion) {
        this.origenImportacion = origenImportacion;
    }

    public Date getFechaProcesamiento() {
        return fechaProcesamiento;
    }

    public void setFechaProcesamiento(Date fechaProcesamiento) {
        this.fechaProcesamiento = fechaProcesamiento;
    }

    public EstadoProcesamiento getEstadoProcesamiento() {
        return estadoProcesamiento;
    }

    public void setEstadoProcesamiento(EstadoProcesamiento estadoProcesamiento) {
        this.estadoProcesamiento = estadoProcesamiento;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}