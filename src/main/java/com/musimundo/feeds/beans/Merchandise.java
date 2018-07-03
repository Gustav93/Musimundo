package com.musimundo.feeds.beans;

import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Utils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MERCHANDISE")
public class Merchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SOURCE", nullable = false)
    private String source;

    @Column(name = "REF_TYPE")
    private String refType;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "RELATIONSHIP")
    private String relationship;

    @Column(name = "QUALIFIER")
    private String qualifier;

    @Column(name = "PRESELECTED")
    private String preselected;

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

    public Merchandise()
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
    	values+="'"+ source+"', ";
    	values+="'"+refType+"', ";
    	values+=target+", ";
    	values+="'"+relationship+"', ";
    	values+="'"+qualifier+"', ";
    	values+="'"+preselected+"', ";
    	values+="'"+importOrigin+"', ";
    	//values+="date('"+Utils.getDateString(processingDate)+"'), ";
        values+="null, ";
    	values+=feedStatus.ordinal()+", ";
    	values+="'"+errorDescription+"', ";
    	values+="'"+company+"', ";   	
    	values+=processed;
		values += ")";
		return values;
    }
}

