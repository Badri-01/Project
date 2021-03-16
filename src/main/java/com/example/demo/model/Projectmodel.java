package com.example.demo.model;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
/*import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;*/
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "projects")
public class Projectmodel {
	@Id
	private String projectId;
	@NotNull
	private String name;
	private String desc;
	@NotNull
	private String status;
	@NotNull
	private Date startdate;
	@NotNull
	private Date enddate;
	@NotNull
	private Date targetedrelease;
	
	public String getId() {
		return projectId;
	}
	public void setId(String projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Date getTargetedrelease() {
		return targetedrelease;
	}
	public void setTargetedrelease(Date targetedrelease) {
		this.targetedrelease = targetedrelease;
	}
	
	

}
