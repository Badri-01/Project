package com.project.app.exception.resource;

@SuppressWarnings("serial")
public class ResourceAlreadyExistsException extends Exception{

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
	
}
