package com.musimundo.feeds.beans;

import com.musimundo.utilities.FeedType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AUDIT")
public class Audit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "INFO")
    private String info;

    @Column(name = "AUDIT_TYPE")
    private String auditType;

    @Column(name = "AUDIT_DATE")
    private Date auditDate;

    @Column(name = "ERROR_CODE")
    private String errorCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name = "IMPORT_ORIGIN")
    private String importOrigin;

    @Column(name = "FEED_TYPE")
    private FeedType feedType;

    public Audit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public void setAuditDate(Date auditDate) {
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getImportOrigin() {
        return importOrigin;
    }

    public void setImportOrigin(String importOrigin) {
        this.importOrigin = importOrigin;
    }

    public FeedType getFeedType() {
        return feedType;
    }

    public void setFeedType(FeedType feedType) {
        this.feedType = feedType;
    }
}
