package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Role;


public interface RoleRepository extends MongoRepository<Role,String>{
	Role findByRoleName(String roleName);
}
