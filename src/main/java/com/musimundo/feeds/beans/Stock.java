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

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "WAREHOUSE")
    private String warehouse;

    @Column(name = "STATUS")
    private String status;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
}
