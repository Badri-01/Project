package com.example.demo.repository;



import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Privilege;

public interface PrivilegeRepository extends MongoRepository<Privilege,String>{
	Privilege findByRoleNameAndResourceName(String roleName,String resourceName);

	ArrayList<Privilege> findByRoleName(String roleName);

	List<Privilege> findByResourceName(String resourceName);
}
