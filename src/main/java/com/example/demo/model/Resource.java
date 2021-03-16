package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "resources")
public class Resource {
	@Id
	private String resourceId;
	private String resourceName;
	
	public Resource(@JsonProperty("resourceName") String resourceName) { //@JsonProperty is needed in case of a single parameter constructor.
		this.resourceName=resourceName;
	}

	public String getResourceName() {
		return resourceName;
	}
	
}
