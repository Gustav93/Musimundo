package com.musimundo.feeds.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historico_productos")
public class OldProduct {

    @Id
    @Column(name = "code")
    private String codigoProducto;

    @Column(name = "ean")
    private String ean;

    @Column(name = "brand")
    private String brand;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "weight")
    private String weight;

    @Column(name = "onlineDateTime")
    private String onlineDateTime;

    @Column(name = "offlineDateTime")
    private String offlineDateTime;

    @Column(name = "approvalStatus")
    private String approvalStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "importOrigin")
    private String origenImportacion;

    @Column(name = "processed")
    private String estadoProcesamiento;

    @Column(name = "errorDescription")
    private String descripcionError;

    @Column(name = "empresa")
    private String empresa;


    public OldProduct()
    {
        this.ean = null;
        this.brand = null;
        this.name = null;
        this.description = null;
        this.weight = null;
        this.onlineDateTime = null;
        this.offlineDateTime = null;
        this.approvalStatus = null;
        this.category = null;
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

    public String getEan() {
        return ean;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getOnlineDateTime() {
        return onlineDateTime;
    }

    public String getOfflineDateTime() {
        return offlineDateTime;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public String getWeight() {
        return weight;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOnlineDateTime(String onlineDateTime) {
        this.onlineDateTime = onlineDateTime;
    }

    public void setOfflineDateTime(String offlineDateTime) {
        this.offlineDateTime = offlineDateTime;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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
        final StringBuffer sb = new StringBuffer("OldProduct{");
        sb.append("codigoProducto='").append(codigoProducto).append('\'');
        sb.append(", ean='").append(ean).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", weight='").append(weight).append('\'');
        sb.append(", onlineDateTime='").append(onlineDateTime).append('\'');
        sb.append(", offlineDateTime='").append(offlineDateTime).append('\'');
        sb.append(", approvalStatus='").append(approvalStatus).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", origenImportacion='").append(origenImportacion).append('\'');
        sb.append(", estadoProcesamiento='").append(estadoProcesamiento).append('\'');
        sb.append(", descripcionError='").append(descripcionError).append('\'');
        sb.append(", empresa='").append(empresa).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

