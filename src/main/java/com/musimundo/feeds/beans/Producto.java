package com.musimundo.feeds.beans;

import com.musimundo.utilities.EstadoProcesamiento;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCTOS")
public class Producto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODIGO_PRODUCTO")
    private String codigoProducto;

    @Column(name = "EAN")
    private String ean;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private Integer category;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "ONLINE_DATE_TIME")
    private Date onlineDateTime;

    @Column(name = "OFFLINE_DATE_TIME")
    private Date offlineDateTime;

    @Column(name = "APPROVAL_STATUS")
    private String approvalStatus;

    @Column(name = "DESCRIPTION")
    private String description;

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

    public Producto()
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

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getOnlineDateTime() {
        return onlineDateTime;
    }

    public void setOnlineDateTime(Date onlineDateTime) {
        this.onlineDateTime = onlineDateTime;
    }

    public Date getOfflineDateTime() {
        return offlineDateTime;
    }

    public void setOfflineDateTime(Date offlineDateTime) {
        this.offlineDateTime = offlineDateTime;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", codigoProducto=" + codigoProducto +
                ", ean=" + ean +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", weight=" + weight +
                ", onlineDateTime=" + onlineDateTime +
                ", offlineDateTime=" + offlineDateTime +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", description='" + description + '\'' +
                ", origenImportacion='" + origenImportacion + '\'' +
                ", fechaProcesamiento=" + fechaProcesamiento +
                ", estadoProcesamiento='" + estadoProcesamiento + '\'' +
                ", descripcionError='" + descripcionError + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }
}