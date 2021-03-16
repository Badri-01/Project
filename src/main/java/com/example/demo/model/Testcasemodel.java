package com.example.demo.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "testcase")
public class Testcasemodel {
	@Id
	private String testcaseid;
	@NotNull
	private String reqid;
	@NotNull
	private String projectid;
	@NotNull
	private String testcasename;
	private String testcasedesc;
	@NotNull
	private String input;
	@NotNull
	private String expectedresults;
	@NotNull
	private String actualresults;
	@NotNull
	private String status;
	private LocalDateTime lastupdated;
	private boolean isDeleted;
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
	public LocalDateTime getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(LocalDateTime lastupdated) {
		this.lastupdated = lastupdated;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
