package com.project.app.model;



import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("defects")

public class Defects {
	
	@Id
	 private String defectId;
	private String createDate;
	@NotNull(message ="Project id should be provided")
	 private String projectId;
	@NotNull(message ="TestCase id should be provided")
	 private String testCaseId;
	@NotNull(message ="Expected result should be provided")
	 private String expectedResult;
	@NotNull(message ="Actual result should be provided")
	 private String actualResult;
	@NotNull(message ="User Id should be provided")
	 private String userId;
	
	 private String status;
	private String lastUpdateDate;
   public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
private int severity;
@Autowired
   private List<Comments> comment;
 private String[] attachementlinks;
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
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public List<Comments> getComment() {
		return comment;
	}
	public void setComment(List<Comments> comment) {
		this.comment = comment;
	}
	public String[] getAttachementlinks() {
		return attachementlinks;
	}
	public void setAttachementlinks(String[] attachementlinks) {
		this.attachementlinks = attachementlinks;
	}
	public String getLastUpdateDate() {
		// TODO Auto-generated method stub
		return lastUpdateDate;
	}
	


	  
	
	
	
}
