package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProjectNotfoundexception;
import com.example.demo.exception.RequirementNotFoundexception;
import com.example.demo.exception.TestcaseNotfoundException;
import com.example.demo.model.Projectmodel;
import com.example.demo.model.Requirementmodel;
import com.example.demo.model.Testcasemodel;
import com.example.demo.service.Projectservices;
import com.example.demo.service.Requirementservices;
import com.example.demo.service.Testcaseservices;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	private Projectservices service;
	@Autowired
	public ProjectController(Projectservices service) {
		this.service=service;
	}
	
	@Autowired
	private Requirementservices reqservice;
	
	@Autowired
	private Testcaseservices testcaseservice;
	
	//Create a new project
	@PostMapping(value="/")
	public ResponseEntity<Projectmodel> createnewproject(@Valid @RequestBody Projectmodel project){
		service.createproject(project);
		return ResponseEntity.ok(project);
	}
	//Get a project by id;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Projectmodel> getprojectbyid(@PathVariable(name="id") String id) throws ProjectNotfoundexception{
		Projectmodel project=service.getprojectbyid(id);
		if(project!=null) {
			 return ResponseEntity.ok(project);
		}
		throw new ProjectNotfoundexception(id);
	}
	
	//List all projects
	@GetMapping(value="/")
	public ResponseEntity<List> getallprojects(){
		return ResponseEntity.ok(service.getallprojects());
	}
	
	//Edit project by id
	@PutMapping(value="/{id}")
	public ResponseEntity<String> editprojectbyid(@PathVariable(name="id") String id,@Valid @RequestBody Projectmodel project ) throws ProjectNotfoundexception{
		if(service.editprojectbyid(id,project)) {
			return ResponseEntity.ok("Project edited successfully");
		}
		throw new ProjectNotfoundexception(id);
	}
	
	//Create new requirement
	@PostMapping(value="/req")
	public ResponseEntity<Requirementmodel> createrequirement(@Valid @RequestBody Requirementmodel requirement) throws ProjectNotfoundexception
	{
		return ResponseEntity.ok(reqservice.createrequirement(requirement));
		
	}
	
	//Get all requirements by project id
	@GetMapping(value="/{projectid}/req")
	public ResponseEntity<List> getrequirementsbyprojectid(@PathVariable(name="projectid")String projectid){
		return ResponseEntity.ok(reqservice.getreqsbyprojectid(projectid));
	}
	
	//Get requirement by id
	@GetMapping(value="/{projectid}/req/{reqid}")
	public ResponseEntity<Requirementmodel> getrequirementbyid(@PathVariable(name="reqid") String reqid) throws RequirementNotFoundexception {
		Requirementmodel req= reqservice.getrequirementbyid(reqid);
		if(req!=null) {
			return ResponseEntity.ok(req);
			}
		throw new RequirementNotFoundexception(reqid);
	}
	
	//Edit requirement by id
	
	@PutMapping(value="/{projectid}/req/{reqid}")
	public ResponseEntity<String> editrequirementbyid(@PathVariable(name="reqid") String reqid,@PathVariable(name="projectid") String projectid,@Valid @RequestBody Requirementmodel requirement) throws RequirementNotFoundexception{
		if(reqservice.editrequirementbyid(reqid, projectid,requirement)) {
			return ResponseEntity.ok("Requirement edited successfully");
		}
		throw new RequirementNotFoundexception(reqid);
	}
	
	//Delete requirement by id
	@DeleteMapping(value="/{projectid}/req/{reqid}")
	public  ResponseEntity<String> deleterequirementbyid(@PathVariable(name="reqid") String reqid) throws RequirementNotFoundexception {
		reqservice.deletereqbyid(reqid);
		return  ResponseEntity.ok("Requirement deleted");
	}
	
	//Create testcase for a requirement
	@PostMapping(value="/req/testcase")
	public ResponseEntity<Testcasemodel> createtestcase(@Valid @RequestBody Testcasemodel testcase) throws ProjectNotfoundexception, RequirementNotFoundexception{
		return ResponseEntity.ok(testcaseservice.createtestcase(testcase));
	}
	
	//Get all testcases associated with a project
	@GetMapping(value="/{projectid}/testcase")
	public ResponseEntity gettestcasesbyprojectid(@PathVariable(name="projectid") String projectid) {
		return ResponseEntity.ok(testcaseservice.gettestcasebyprojectid(projectid));
	}
	
	//Get all testcases by requirement id
	@GetMapping(value="/{projectid}/req/{reqid}/testcase")
	public ResponseEntity gettestcasesbyrequirementid(@PathVariable(name="reqid") String reqid) {
		return ResponseEntity.ok(testcaseservice.gettestcasesbyrequirementid(reqid));
	}
	
	//Get testcase by testcase id
	
	@GetMapping(value="/{projectid}/req/{reqid}/testcase/{testcaseid}")
	public ResponseEntity gettestcasebyid(@PathVariable(name = "testcaseid") String testid) throws TestcaseNotfoundException {
		Testcasemodel testcase=testcaseservice.gettestcasebyid(testid);
		if(testcase!=null) {
			return ResponseEntity.ok(testcase);
		}
		throw new TestcaseNotfoundException(testid);
	}
	
	//Edit Testcase by testcaseid
	@PutMapping(value="/{projectid}/req/{reqid}/testcase/{testcaseid}")
	public ResponseEntity edittestcase(@PathVariable(name="testcaseid") String testid, @Valid @RequestBody Testcasemodel testcase) throws TestcaseNotfoundException
	{
		return ResponseEntity.ok(testcaseservice.edittestcase(testid, testcase));
	}
	
}