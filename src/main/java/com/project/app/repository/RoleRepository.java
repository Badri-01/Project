package com.project.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.app.model.Role;


public interface RoleRepository extends MongoRepository<Role,String>{
	
	@Query(value="{roleName:?0,roleStatus:{$ne:'Inactive'}}")
	Role findByRoleName(String roleName);
	
	@Query(value="{roleStatus:{$ne:'Inactive'}}")
	List<Role> findAll();
	
	@Query(value="{roleStatus:{$ne:'Inactive'}}",exists=true)
	boolean existsByName(String roleName);
}
