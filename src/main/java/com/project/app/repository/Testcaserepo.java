package com.project.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.app.model.Testcasemodel;

import java.util.List;

public interface Testcaserepo extends MongoRepository<Testcasemodel, String> {
	
	@Query(value="{projectid:?0,reqid:?1}",sort = "{_id:-1}")
	List<Testcasemodel> findlastinserteddocs(String projectid,String reqid);
	
	
	@Query(value="{projectid:?0,isDeleted:{$ne:true}}")
	List<Testcasemodel> findByProjectid(String projectid);
	
	@Query(value="{reqid:?0,isDeleted:{$ne:true}}")
	List<Testcasemodel> findByReqid(String reqid);
	
	@Query(value="{testcaseid:?0,isDeleted:{$ne:true}}")
	Testcasemodel findByTestcaseid(String testid);
	
	//@Query(value="{isDeleted:{$ne:true}}")
	boolean existsById(String testid);
}
