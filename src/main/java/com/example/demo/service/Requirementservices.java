package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.ProjectingArgumentResolverRegistrar;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProjectNotfoundexception;
import com.example.demo.exception.RequirementNotFoundexception;
import com.example.demo.model.Projectmodel;
import com.example.demo.model.Requirementmodel;
import com.example.demo.model.SequenceGenerator;
import com.example.demo.model.Testcasemodel;
import com.example.demo.repository.Repository;
import com.example.demo.repository.Requirementrepo;
import com.example.demo.repository.Testcaserepo;

@Service
public class Requirementservices {
	
	@Autowired
	private Requirementrepo mongorepo;
	@Autowired
	private Repository projectrepo;
	@Autowired
	private Testcaserepo testcaserepo;
	
	//Create requirements
	public Requirementmodel createrequirement(Requirementmodel requirement) throws ProjectNotfoundexception
	{
		Projectmodel project=projectrepo.findByProjectId(requirement.getProjectid());
		if(project!=null)
		 {
		List<Requirementmodel> requirements=mongorepo.findlastinserteddocs(requirement.getProjectid());
		if(requirements.size()==0) {
			requirement.setReqid(requirement.getProjectid()+"-R-1");
			mongorepo.insert(requirement);
		}
		else {
			requirement.setReqid(requirement.getProjectid()+'-'+SequenceGenerator.generateID(requirements.get(0).getReqid()));
			mongorepo.insert(requirement);
			
		}
		
		return requirement;
		}
		throw new ProjectNotfoundexception(requirement.getProjectid());		
	}
	
	//Get requirements by projectid
	public List<Requirementmodel> getreqsbyprojectid(String projectid){
		return mongorepo.findrequirementsbyProjectid(projectid);
	}
	
	//Get requirement by reqid
	public Requirementmodel getrequirementbyid(String reqid) {
		return mongorepo.findByReqid(reqid);
	}
	
	//Edit requirement by reqid
	public boolean editrequirementbyid(String reqid,String projectid,Requirementmodel requirement){
		if(mongorepo.existsById(reqid)) {
			requirement.setReqid(reqid);
			requirement.setProjectid(projectid);
			mongorepo.save(requirement);
			return true;
			
		}
		return false;
	}
	
	//Delete requirement by reqid
	public void deletereqbyid(String reqid) throws RequirementNotFoundexception {
		Requirementmodel requirement=mongorepo.findByReqid(reqid);
		if(requirement==null) {
			throw new RequirementNotFoundexception(reqid);
		}
		requirement.setDeleted(true);
		//Cascade all testcases
		List<Testcasemodel> testcases=testcaserepo.findByReqid(reqid);
		for(Testcasemodel testcase:testcases) {
			testcase.setDeleted(true);
		}
		testcaserepo.saveAll(testcases);		
		mongorepo.save(requirement);
		
	}
	
}
