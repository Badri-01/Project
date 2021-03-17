package com.project.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.app.exception.ProjectNotfoundexception;
import com.project.app.exception.RequirementNotFoundexception;
import com.project.app.exception.TestcaseNotfoundException;
import com.project.app.model.Projectmodel;
import com.project.app.model.Requirementmodel;
import com.project.app.model.SequenceGenerator;
import com.project.app.model.Testcasemodel;
import com.project.app.repository.Repository;
import com.project.app.repository.Requirementrepo;
import com.project.app.repository.Testcaserepo;

@Service
public class Testcaseservices {

	@Autowired
	private Testcaserepo mongorepo;
	
	@Autowired
	private Repository projectrepo;
	
	@Autowired
	private Requirementrepo reqrepo;
	
	public Testcasemodel createtestcase(Testcasemodel testcase) throws ProjectNotfoundexception, RequirementNotFoundexception {
		String projectid=testcase.getProjectid();
		String reqid=testcase.getReqid();
		Projectmodel project=projectrepo.findByProjectId(projectid);
		Requirementmodel req=reqrepo.findByReqid(reqid);
		if(project!=null) {
			if(req!=null) {
		List<Testcasemodel> testcases=mongorepo.findlastinserteddocs(projectid, reqid);
		String []arr=reqid.split("-");
		if(testcases.size()==0) {
			testcase.setTestcaseid(projectid+"-"+arr[arr.length-2]+"-"+arr[arr.length-1]+"-"+"T-1");
			testcase.setLastupdated(java.time.LocalDateTime.now());
		}
		else {
			String lastid=testcases.get(0).getTestcaseid();
			String newtestcaseid=SequenceGenerator.generateID(lastid);
			testcase.setTestcaseid(projectid+"-"+arr[arr.length-2]+"-"+arr[arr.length-1]+"-"+newtestcaseid);
			testcase.setLastupdated(java.time.LocalDateTime.now());
		}
		mongorepo.insert(testcase);
		return testcase;
		}
			throw new RequirementNotFoundexception(reqid);
		}
		throw new ProjectNotfoundexception(projectid);
		
	}
	
	//Get all testcases by project id
	public List<Testcasemodel> gettestcasebyprojectid(String projectid) {
		return mongorepo.findByProjectid(projectid);
	}
	
	//get all testcases by reqid
	
	public List<Testcasemodel> gettestcasesbyrequirementid(String reqid){
		return mongorepo.findByReqid(reqid);
	}
	
	//get testcase by id
	public Testcasemodel gettestcasebyid(String testid) {
		return mongorepo.findByTestcaseid(testid);
	}
	
	//edit testcase by id
	public String edittestcase(String testid,Testcasemodel testcase) throws TestcaseNotfoundException {
		if(mongorepo.existsById(testid)) {
			testcase.setTestcaseid(testid);
			mongorepo.save(testcase);
			return "Edited successfully";
		}
		throw new TestcaseNotfoundException(testid);
	}
	
}
