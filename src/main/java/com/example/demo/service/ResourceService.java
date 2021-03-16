package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ResourceRepository;
import com.example.demo.model.Resource;

@Service
public class ResourceService {
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	public void addResource(Resource resource) {
		resourceRepository.insert(resource);
	}

	public List<Resource> getAllResources() {
		return resourceRepository.findAll();
	}
	
	public Resource removeResource(String resourceName) {
		Resource resource = resourceRepository.findByResourceName(resourceName);
		if(resource==null)
			return null;
		resourceRepository.delete(resource);
		return resource;
	}
	
}
