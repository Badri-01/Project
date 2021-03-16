package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProjectNotfoundexception;
import com.example.demo.exception.RequirementNotFoundexception;
import com.example.demo.exception.TestcaseNotfoundException;
import com.example.demo.model.Projectmodel;
import com.example.demo.model.Requirementmodel;
import com.example.demo.model.SequenceGenerator;
import com.example.demo.model.Testcasemodel;
import com.example.demo.repository.Repository;
import com.example.demo.repository.Requirementrepo;
import com.example.demo.repository.Testcaserepo;

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
