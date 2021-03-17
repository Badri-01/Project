package com.project.app.exception.privilege;

@SuppressWarnings("serial")
public class PrivilegeAlreadyExistsException extends Exception{

	public PrivilegeAlreadyExistsException(String message) {
		super(message);
	}
	
}
