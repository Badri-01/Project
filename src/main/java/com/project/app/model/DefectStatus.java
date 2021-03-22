package com.project.app.model;

import org.springframework.stereotype.Component;

@Component
public class DefectStatus {
	private String status;
	private int severity;
	
	private String userId;
	private String updateDate;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String fromDate) {
		this.updateDate = fromDate;
	}
	
	
}
