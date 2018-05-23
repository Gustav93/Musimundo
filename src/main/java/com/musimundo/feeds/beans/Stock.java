package com.musimundo.feeds.beans;

import com.musimundo.utilities.FeedStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STOCK")
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODIGO_PRODUCTIO")
    private Integer codigoProducto;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "WAREHOUSE")
    private String warehouse;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ORIGEN_IMPORTACION")
    private String origenImportacion;

    @Column(name = "FECHA_PROCESAMIENTO")
    private Date fechaProcesamiento;

    @Column(name = "ESTADO_PROCESAMIENTO")
    private FeedStatus feedStatus;

    @Column(name = "DESCRIPCION_ERROR")
    private String descripcionError;

    @Column(name = "EMPRESA")
    private String empresa;

    public Stock()
    {
        this.feedStatus = FeedStatus.NOT_PROCESSED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
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

    public void setStatus(String status) {
        this.status = status;
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

    public FeedStatus getFeedStatus() {
        return feedStatus;
    }

    public void setFeedStatus(FeedStatus feedStatus) {
        this.feedStatus = feedStatus;
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
