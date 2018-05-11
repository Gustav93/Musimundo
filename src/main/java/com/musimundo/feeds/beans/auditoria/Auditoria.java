package com.musimundo.feeds.beans.auditoria;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AUDITORIA")
public class Auditoria implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "AUDIT_LEVEL")
    private String auditLevel;

    @Column(name = "AUDIT_TYPE")
    private String auditType;

    @Column(name = "AUDIT_DATE")
    private Date auditDate;

    @NotEmpty
    @Column(name = "ERROR_CODE")
    private String errorCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotEmpty
    @Column(name = "EMPRESA")
    private String empresa;

    @NotEmpty
    @Column(name = "CODIGO_PRODUCTO")
    private String codigoProducto;

    @NotEmpty
    @Column(name = "ORIGEN_IMPORTACION")
    private String origenImportacion;

    @NotEmpty
    @Column(name = "FEED_TYPE")
    private String feedType;

    public Auditoria()
    {
        this.auditLevel = null;
        this.auditType = null;
        this.auditDate = null;
        this.errorCode = null;
        this.description = null;
        this.empresa = null;
        this.codigoProducto = null;
        this.origenImportacion = null;
        this.feedType = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuditLevel() {
        return auditLevel;
    }

    public void setAuditLevel(String auditLevel) {
        this.auditLevel = auditLevel;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate)
    {
        this.auditDate = auditDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    @Override
    public String toString() {
        return "RegistroAuditoria{" +
                "auditLevel='" + auditLevel + '\'' +
                ", auditType='" + auditType + '\'' +
                ", auditDate='" + auditDate + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", description='" + description + '\'' +
                ", empresa='" + empresa + '\'' +
                ", codigoProducto='" + codigoProducto + '\'' +
                ", setOrigenImportacion='" + origenImportacion + '\'' +
                ", feedType='" + feedType + '\'' +
                '}';
    }
}