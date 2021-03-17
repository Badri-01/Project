package com.project.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.app.model.Role;


public interface RoleRepository extends MongoRepository<Role,String>{
	Role findByRoleName(String roleName);
}
