package com.project.app.dto;

import java.util.Date;

import org.springframework.data.annotation.LastModifiedDate;

import com.project.app.model.Testcasemodel;

public class Testcasedto {
	
	public String testcaseid;
	
	public String reqid;
	
	public String projectid;
	
	public String testcasename;
	public String testcasedesc;
	
	public String input;
	
	public String expectedresults;
	
	public String actualresults;
	
	public String status;
	@LastModifiedDate
	public Date lastupdated;
	public boolean isDeleted;
	public String getTestcaseid() {
		return testcaseid;
	}
	public void setTestcaseid(String testcaseid) {
		this.testcaseid = testcaseid;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getTestcasename() {
		return testcasename;
	}
	public void setTestcasename(String testcasename) {
		this.testcasename = testcasename;
	}
	public String getTestcasedesc() {
		return testcasedesc;
	}
	public void setTestcasedesc(String testcasedesc) {
		this.testcasedesc = testcasedesc;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getExpectedresults() {
		return expectedresults;
	}
	public void setExpectedresults(String expectedresults) {
		this.expectedresults = expectedresults;
	}
	public String getActualresults() {
		return actualresults;
	}
	public void setActualresults(String actualresults) {
		this.actualresults = actualresults;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Testcasemodel totestcase() {
		return new Testcasemodel(testcasename,testcasedesc,status,input,expectedresults,actualresults);
	}
	
}
