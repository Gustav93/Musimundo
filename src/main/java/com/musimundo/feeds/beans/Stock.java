package com.musimundo.feeds.beans;

import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Utils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STOCK")
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_CODE", nullable = false)
    private String productCode;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "WAREHOUSE")
    private String warehouse;

    @Column(name = "STATUS")
    private String status;

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

    public Stock()
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

    public boolean getProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
    
    public String toInsert() {
		String values = "(";
    	values+="'"+ productCode+"', ";
    	values+=stock+", ";
    	values+="'"+warehouse+"', ";
    	values+="'"+status+"', ";
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
