package com.project.app.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.app.model.Projectmodel;


public interface Repository extends MongoRepository<Projectmodel,String>{
	
	//Find last inserted docs
	@Query(value="{}",sort = "{_id:-1}")
	List<Projectmodel> findlastinserteddocs();
	
	
	Projectmodel findByProjectId(String id);
	
	
	
}
