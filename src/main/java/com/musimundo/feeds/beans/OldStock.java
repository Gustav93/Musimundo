package com.musimundo.feeds.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historico_stock")
public class OldStock {

    @Id
    @Column(name = "productCode")
    private String productCode;

    @Column(name = "stock")
    private String stock;

    @Column(name = "warehouse")
    private String warehouse;

    @Column(name = "status")
    private String status;

    @Column(name = "importOrigin")
    private String origenImportacion;

    @Column(name = "processed")
    private String estadoProcesamiento;

    @Column(name = "errorDescription")
    private String descripcionError;

    @Column(name = "empresa")
    private String empresa;

    public OldStock()
    {
        this.stock = null;
        this.warehouse = null;
        this.status = null;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getStatus() {
        return status;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
