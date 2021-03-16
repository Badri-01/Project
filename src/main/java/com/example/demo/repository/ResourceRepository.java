package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Resource;

public interface ResourceRepository extends MongoRepository<Resource,String>{

	Resource findByResourceName(String resourceName);

}
