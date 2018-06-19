package com.musimundo.servicemonitor.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SERVICE_CHECKS")
public class ServiceCheck implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;
	
	@NotEmpty
	@Column(name="URL", nullable=false)
	private String url;	
	
	@NotEmpty
	@Column(name="ELAPSED_STRING", nullable=false)
	private String elapsedString;	

	@Column(name="ELAPSED_TIME", nullable=false)
	private long elapsedTime;
	
	
	@Column(name="CHECK_TIME", nullable=false)
	private Date checkTime;
	
	
	@ManyToOne
	@JoinColumn(name="SERVICE_ID")
	private ServiceToCheck service;
	
	@NotEmpty
	@Column(name="CODE_RESPONSE")
	private String codeResponse;
	
	@Column(name="ACTIVE_RESPONSE", nullable=false, columnDefinition = "BIT", length = 1)
	private boolean isActive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public ServiceToCheck getService() {
		return service;
	}

	public void setService(ServiceToCheck service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(String codeResponse) {
		this.codeResponse = codeResponse;
	}

	public String getElapsedString() {
		return elapsedString;
	}

	public void setElapsedString(String elapsedString) {
		this.elapsedString = elapsedString;
	}
	
	

}
