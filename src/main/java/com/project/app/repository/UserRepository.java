package com.project.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.app.model.Role;
import com.project.app.model.User;

public interface UserRepository extends MongoRepository<User,String>{
	User findByUsername(String username);
	@Query("{'roles':?#{[0]}}")
	List<User> findByRole(Role role);
}
