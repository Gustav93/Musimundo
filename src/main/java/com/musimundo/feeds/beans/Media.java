package com.musimundo.feeds.beans;

import com.musimundo.utilities.FeedStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEDIA")
public class Media
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_CODE", nullable = false)
    private String productCode;

    @Column(name = "CODE_MEDIA")
    private String codeMedia;

    @Column(name = "IS_DEFAULT")
    private boolean isDefault;

    @Column(name = "IMPORT_ORIGIN", nullable = false)
    private String importOrigin;

    @Column(name = "PROCESSING_DATE")
    private Date processingDate;

    @Column(name = "FEED_STATUS", nullable = false)
    private FeedStatus feedStatus;

    @Column(name = "ERROR_DESCRIPTION")
    private String errorDescription;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "PROCESSED", nullable = false)
    private boolean processed;

    public Media()
    {
        this.feedStatus = FeedStatus.NOT_PROCESSED;
        this.processed = false;
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

    public String getCodeMedia() {
        return codeMedia;
    }

    public void setCodeMedia(String codeMedia) {
        this.codeMedia = codeMedia;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
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

    public boolean getProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}