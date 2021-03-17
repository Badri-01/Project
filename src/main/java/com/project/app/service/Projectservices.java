package com.project.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.app.model.Projectmodel;
import com.project.app.model.SequenceGenerator;
import com.project.app.repository.Repository;


@Service
public class Projectservices {
	@Autowired
	private Repository mongorepo;
	
	public void createproject(Projectmodel project) {
		List<Projectmodel> projectlist=mongorepo.findlastinserteddocs();
		if(projectlist.size()==0) {
			project.setId("PR-1");
			mongorepo.insert(project);
		}
		else {
			Projectmodel lastproject=projectlist.get(0);
			String id=lastproject.getId(); 
			String newid=SequenceGenerator.generateID(id);
			project.setId(newid);
			mongorepo.insert(project);
			
		}
		
	}
	
	public Projectmodel getprojectbyid(String id) {
		return mongorepo.findByProjectId(id);
	}
	
	public List<Projectmodel> getallprojects(){
		return mongorepo.findAll();
	}
	
	public boolean editprojectbyid(String id,Projectmodel project) {
		
		if(mongorepo.existsById(id))
		{
			project.setId(id);
			mongorepo.save(project);
			return true;
		}
		return false;
		
	}
}
