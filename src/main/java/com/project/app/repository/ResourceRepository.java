package com.project.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.app.model.Resource;

public interface ResourceRepository extends MongoRepository<Resource,String>{

	Resource findByResourceName(String resourceName);

}
