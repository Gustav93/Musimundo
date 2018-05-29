package com.musimundo.feeds.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historico_precios")
public class OldPrice {

    @Id
    @Column(name = "productCode")
    private String codigoProducto;

    @Column(name = "onlinePrice")
    private String onlinePrice;

    @Column(name = "currency")
    private String currency;

    @Column(name = "storePrice")
    private String storePrice;

    @Column(name = "hasPriority")
    private String hasPriority;

    @Column(name = "importOrigin")
    private String origenImportacion;

    @Column(name = "processed")
    private String estadoProcesamiento;

    @Column(name = "errorDescription")
    private String descripcionError;

    @Column(name = "empresa")
    private String empresa;


    public OldPrice()
    {
        this.onlinePrice = null;
        this.currency = null;
        this.storePrice = null;
        this.hasPriority = null;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(String onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public String getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(String storePrice) {
        this.storePrice = storePrice;
    }

    public String getHasPriority() {
        return hasPriority;
    }

    public void setHasPriority(String hasPriority) {
        this.hasPriority = hasPriority;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getOrigenImportacion() {
        return origenImportacion;
    }

    public void setOrigenImportacion(String origenImportacion) {
        this.origenImportacion = origenImportacion;
    }

    public String getEstadoProcesamiento() {
        return estadoProcesamiento;
    }

    public void setEstadoProcesamiento(String estadoProcesamiento) {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OldPrice{");
        sb.append("codigoProducto='").append(codigoProducto).append('\'');
        sb.append(", onlinePrice='").append(onlinePrice).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", storePrice='").append(storePrice).append('\'');
        sb.append(", hasPriority='").append(hasPriority).append('\'');
        sb.append(", origenImportacion='").append(origenImportacion).append('\'');
        sb.append(", estadoProcesamiento='").append(estadoProcesamiento).append('\'');
        sb.append(", descripcionError='").append(descripcionError).append('\'');
        sb.append(", empresa='").append(empresa).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
