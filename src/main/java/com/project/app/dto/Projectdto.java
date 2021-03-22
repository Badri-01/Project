package com.project.app.dto;

import java.util.Date;

import com.project.app.exception.DatesMismatchException;
import com.project.app.model.Projectmodel;



public class Projectdto {
	private String projectId;
	private String name;
	private String desc;
	private String status;
	private Date startdate;
	private Date enddate;
	private Date targetedrelease;
	
	public Projectdto(String name, String desc, String status, String projectId, Date startdate, Date enddate, Date targetedrelease) {
		this.projectId=projectId;
		this.name=name;
		
		this.desc=desc;
		this.status=status;
		this.startdate=startdate;
		this.enddate=enddate;
		this.targetedrelease=targetedrelease;
		// TODO Auto-generated constructor stub
	}
	public Projectmodel toProject() throws DatesMismatchException {
		return new Projectmodel(name,desc,status,startdate,enddate,targetedrelease);
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
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
