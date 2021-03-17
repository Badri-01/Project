package com.project.app.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "history")
public class Historymodel {
	
	@Id
	private String timeofscheduler;
	private int nooftestcasesopened;
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
	
	
}
