package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public interface UserRepository extends MongoRepository<User,String>{
	User findByUsername(String username);
	@Query("{'roles':?#{[0]}}")
	List<User> findByRole(Role role);
}
