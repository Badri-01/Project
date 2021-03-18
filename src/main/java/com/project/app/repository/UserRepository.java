package com.project.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.app.model.User;

public interface UserRepository extends MongoRepository<User,String>{
	
	@Query(value="{username:?0,status:{$ne:'Inactive'}}")
	User findByUsername(String username);
	
	@Query(value="{status:{$ne:'Inactive'}}")
	List<User> findAll();
	
	//@Query("{'roles':?#{[0]}}")
	//List<User> findByRole(Role role);
}
