package com.musimundo.feeds.beans;

import com.musimundo.utilities.FeedStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

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

    @Column(name = "IMPORT_ORIGIN")
    private String importOrigin;

    @Column(name = "PROCESSING_DATE")
    private Date processingDate;

    @Column(name = "FEED_STATUS")
    private FeedStatus feedStatus;

    @Column(name = "ERROR_DESCRIPTION")
    private String errorDescription;

    @Column(name = "COMPANY")
    private String company;

    public Product()
    {
        this.feedStatus = FeedStatus.NOT_PROCESSED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public String getImportOrigin() {
        return importOrigin;
    }

    public void setImportOrigin(String importOrigin) {
        this.importOrigin = importOrigin;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public FeedStatus getFeedStatus() {
        return feedStatus;
    }

    public void setFeedStatus(FeedStatus feedStatus) {
        this.feedStatus = feedStatus;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productCode=" + productCode +
                ", ean=" + ean +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", weight=" + weight +
                ", onlineDateTime=" + onlineDateTime +
                ", offlineDateTime=" + offlineDateTime +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", description='" + description + '\'' +
                ", importOrigin='" + importOrigin + '\'' +
                ", processingDate=" + processingDate +
                ", feedStatus='" + feedStatus + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}