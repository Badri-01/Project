package com.project.app.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "history")
public class Historymodel {
	
	@Id
	private String timeofscheduler;
	private int nooftestcasesopened;
	private int nooftestcasepassed;
	private int nooftestcasesfailed;
	private int totalnooftestcases;
	private int totalNoofDefects;
	private int noofOpenDefects;
	private int noofCloseDefects;
	public int getNooftestcasepassed() {
		return nooftestcasepassed;
	}
	public void setNooftestcasepassed(int nooftestcasepassed) {
		this.nooftestcasepassed = nooftestcasepassed;
	}
	public int getNooftestcasesfailed() {
		return nooftestcasesfailed;
	}
	public void setNooftestcasesfailed(int nooftestcasesfailed) {
		this.nooftestcasesfailed = nooftestcasesfailed;
	}
	private Date lastupdatedDate;
	public String getTimeofscheduler() {
		return timeofscheduler;
	}
	public void setTimeofscheduler(String timeofscheduler) {
		this.timeofscheduler = timeofscheduler;
	}
	public int getNooftestcasesopened() {
		return nooftestcasesopened;
	}
	public void setNooftestcasesopened(int nooftestcasesopened) {
		this.nooftestcasesopened = nooftestcasesopened;
	}
	public Date getLastupdatedDate() {
		return lastupdatedDate;
	}
	public void setLastupdatedDate(Date lastupdatedDate) throws ParseException {
		SimpleDateFormat df=new SimpleDateFormat("mm-dd-yy");
		this.lastupdatedDate = df.parse(df.format(lastupdatedDate));
	}
	public int getTotalnooftestcases() {
		return totalnooftestcases;
	}
	public void setTotalnooftestcases(int totalnooftestcases) {
		this.totalnooftestcases = totalnooftestcases;
	}
	public int getTotalNoofDefects() {
		return totalNoofDefects;
	}
	public void setTotalNoofDefects(int totalNoofDefects) {
		this.totalNoofDefects = totalNoofDefects;
	}
	public int getNoofOpenDefects() {
		return noofOpenDefects;
	}
	public void setNoofOpenDefects(int noofOpenDefects) {
		this.noofOpenDefects = noofOpenDefects;
	}
	public int getNoofCloseDefects() {
		return noofCloseDefects;
	}
	public void setNoofCloseDefects(int noofCloseDefects) {
		this.noofCloseDefects = noofCloseDefects;
	}
	
	
}
