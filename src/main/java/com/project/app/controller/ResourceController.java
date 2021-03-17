package com.project.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.exception.resource.ResourceAlreadyExistsException;
import com.project.app.exception.resource.ResourceNotFoundException;
import com.project.app.model.Resource;
import com.project.app.service.ResourceService;

@RequestMapping("/api/v1/resource/")
@RestController	
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@PreAuthorize("hasAccess('resource','read')")
	@GetMapping
	public List<Resource> getAllResources() {
		return resourceService.getAllResources();
	}
	
	@PreAuthorize("hasAccess('resource','write')")
	@PostMapping
	public @ResponseBody String createResource(@Valid @RequestBody Resource resource) throws ResourceAlreadyExistsException{
		try {
			resourceService.addResource(resource);
		}
		catch(DuplicateKeyException ex) {
			//System.out.println(ex.getClass());
			throw new ResourceAlreadyExistsException(resource.getResourceName());
		}
		return resource.getResourceName()+" resource added";
	}
	
	@PreAuthorize("hasAccess('resource','delete')")
	@DeleteMapping(path = "{resourceName}")
	public String deleteResource(@PathVariable("resourceName") String resourceName) throws ResourceNotFoundException{
		Resource resource=resourceService.removeResource(resourceName);
		if(resource!=null)
			return "Resource "+resource.getResourceName()+" deleted";
		throw new ResourceNotFoundException(resourceName);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleResourceNotFoundException(ResourceNotFoundException ex)
	{
	  return "Resource "+ex.getMessage()+" not found";
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex)
	{
	  return "Resource "+ ex.getMessage()+" already exists";
	}
}
