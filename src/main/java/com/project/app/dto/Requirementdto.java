package com.project.app.dto;

import com.project.app.model.Requirementmodel;

public class Requirementdto {
	//Fields
		
		public String reqid;
		public String reqdesc;
		public String projectid;
		public String status;
		public boolean isDeleted;
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
		public Requirementmodel toRequirement() {
			return new Requirementmodel(reqdesc,status);
		}
}
