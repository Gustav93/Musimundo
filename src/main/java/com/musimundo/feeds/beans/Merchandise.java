package com.musimundo.feeds.beans;

import com.musimundo.utilities.FeedStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MERCHANDISE")
public class Merchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "REF_TYPE")
    private String refType;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "RELACION")
    private String relacion;

    @Column(name = "QUALIFIER")
    private String qualifier;

    @Column(name = "PRESELECTED")
    private String preselected;

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

    public Merchandise()
    {
        this.feedStatus = FeedStatus.NOT_PROCESSED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getPreselected() {
        return preselected;
    }

    public void setPreselected(String preselected) {
        this.preselected = preselected;
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

