package com.example.demo.exception.privilege;

@SuppressWarnings("serial")
public class PrivilegeAlreadyExistsException extends Exception{

	public PrivilegeAlreadyExistsException(String message) {
		super(message);
	}
	
}
