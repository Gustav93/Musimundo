package com.musimundo.feeds.beans;

import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Utils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRICE")
public class Price
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_CODE", nullable = false)
    private String productCode;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "ONLINE_PRICE")
    private Double onlinePrice;

    @Column(name = "STORE_PRICE")
    private Double storePrice;

    @Column(name = "HAS_PRIORITY")
    private boolean hasPriority;

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

    public Price()
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(Double onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public Double getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(Double storePrice) {
        this.storePrice = storePrice;
    }

    public boolean getHasPriority() {
        return hasPriority;
    }

    public void setHasPriority(boolean hasPriority) {
        this.hasPriority = hasPriority;
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
    
    public String toInsert() {
		String values = "(";
    	values+="'"+ productCode+"', ";
    	values+="'"+currency+"', ";
    	values+=onlinePrice+", ";
    	values+=storePrice+", ";
    	values+=hasPriority+", ";
    	values+="'"+importOrigin+"', ";
    	values+="date('"+Utils.getDateString(processingDate)+"'), ";
    	values+=feedStatus.ordinal()+", ";
    	values+="'"+errorDescription+"', ";
    	values+="'"+company+"', ";   	
    	values+=processed;
		values += ")";
		return values;
    }
}