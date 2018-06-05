package com.musimundo.servicemonitor.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.musimundo.model.User;

@Entity
@Table(name="SERVICE_TO_CHECK")
public class ServiceToCheck implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name="NAME", unique=true, nullable=false)
	private String name;
	
	@NotEmpty
	@Column(name="URL", nullable=false)
	private String url;
		
	@NotEmpty
	@Column(name="TYPE_REQUEST", nullable=false)
	private String type;

	@NotEmpty
	@Column(name="TIME_OUT", nullable=false)
	private String timeOut;

	
	@Column(name="CREATE_TIME", nullable=false)
	private Date createTime;
	
	
	@Column(name="LAST_CHECK_TIME", nullable=false)
	private Date lastCheckTime;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@NotEmpty
	@Column(name="STATE", nullable=false)
	private String state;	
	
	@Column(name="ELAPSED_TIME", nullable=false)
	private String elapsedTime;	
	
	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		ServiceToCheck other = (ServiceToCheck) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ServiceToCheck [id=" + id + ", name=" + name + ", url=" + url
				+ ", timeOut=" + timeOut + ", type=" + type
				+ ", user=" + user.getSsoId() + ", createTime="+ createTime.toString() 
				+ ", lastCheck="+ lastCheckTime.toString() +"]";
	}

}
