package com.example.demo.exception.resource;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends Exception{

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
