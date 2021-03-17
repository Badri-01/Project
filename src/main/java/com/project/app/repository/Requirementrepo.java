package com.project.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.app.model.Requirementmodel;

public interface Requirementrepo extends MongoRepository<Requirementmodel, String>{

	@Query(value="{projectid:?0}",sort = "{_id:-1}")
	List<Requirementmodel> findlastinserteddocs(String projectid);
	
	@Query(value="{projectid:?0,isDeleted:{$ne:true}}")
	List<Requirementmodel> findrequirementsbyProjectid(String projectid);
	
	@Query(value="{reqid:?0,isDeleted:{$ne:true}}")
	Requirementmodel findByReqid(String reqid);
}
