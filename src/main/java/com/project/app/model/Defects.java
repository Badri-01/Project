package com.project.app.model;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import javax.validation.constraints.NotBlank;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("defects")

public class Defects {
	
	@Id
	 private String defectId;

	private String createDate;
	@NotBlank(message ="Project id should be provided")
	 private String projectId;
	@NotBlank(message ="Project id should be provided")
	 private String testCaseId;
	@NotBlank(message ="Project id should be provided")
	 private String expectedResult;
	@NotBlank(message ="Project id should be provided")
	 private String actualResult;
	@NotBlank(message ="Project id should be provided")
	 private String userId;
	
	 private String status;
	private String lastUpdateDate;
   public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
private int severity;

   private ArrayList<Comments> comment=new ArrayList<>();
   private ArrayList<String> attachementlinks=new ArrayList<>();

 
	public String getDefectId() {
		return defectId;
	}
	public void setDefectId(String defectId) {
		this.defectId = defectId;
	}
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getExpectedResult() {
		return expectedResult;
	}
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	public String getActualResult() {
		return actualResult;
	}
	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) throws InvalidAttributesException {
		List<String> statusArray=new ArrayList<>(Arrays.asList("new","Fixed","Retest","Retest fail","close"));
		if(statusArray.contains(status)) {
			this.status = status;
		}
		else {
			throw new InvalidAttributesException("Status value is not valid provide a valid value(\"new\",\"Fixed\",\"Retest\",\"Retest fail\",\"close\"");
		}
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public ArrayList<Comments> getComment() {
		return comment;
	}
	public void setComment(ArrayList<Comments> comment) {
		this.comment = comment;
	}
	public  ArrayList<String> getAttachementlinks() {
		return attachementlinks;
	}
	public void setAttachementlinks( ArrayList<String> attachementlinks) {
		this.attachementlinks = attachementlinks;
	}
	public String getLastUpdateDate() {
		// TODO Auto-generated method stub
		return lastUpdateDate;
	}
	


	  
	
	
	
}
