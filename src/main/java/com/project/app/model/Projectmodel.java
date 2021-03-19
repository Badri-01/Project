package com.project.app.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
/*import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;*/
import org.springframework.data.mongodb.core.mapping.Document;

import com.project.app.exception.DatesMismatchException;


@Document(collection = "projects")
public class Projectmodel {
	private  SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd");
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
	public Date getStartdate() throws ParseException {
		return df.parse(df.format(startdate));
	}
	public void setStartdate(Date startdate) throws ParseException {
		this.startdate = df.parse(df.format(startdate));
	}
	public Date getEnddate() throws ParseException
	{
		return df.parse(df.format(enddate));
	}
	public void setEnddate(Date enddate) throws DatesMismatchException, ParseException {
		if(checkDates(enddate)) {
		this.enddate = df.parse(df.format(enddate));
	}
		throw new DatesMismatchException("enddate");
	}
	public Date getTargetedrelease() throws ParseException {
		return df.parse(df.format(targetedrelease));
	}
	public void setTargetedrelease(Date targetedrelease) throws DatesMismatchException, ParseException {
		if(checkDates(targetedrelease)) {
		this.targetedrelease = df.parse(df.format(targetedrelease));
	}
	throw new DatesMismatchException("targetedrelease");
	}
	public boolean checkDates(Date date) {
		System.out.println(date+"pog"+startdate);
		if(date.after(startdate)) {
			return true;
		}
		return false;
	}

}
