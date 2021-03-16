package com.example.demo.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "requirements")
public class Requirementmodel {

	//Fields
	@Id
	private String reqid;
	private String reqdesc;
	@NotNull
	private String projectid;
	@NotNull
	private String status;
	private boolean isDeleted;
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getReqdesc() {
		return reqdesc;
	}
	public void setReqdesc(String reqdesc) {
		this.reqdesc = reqdesc;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
