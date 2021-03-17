package com.project.app.exception.role;

@SuppressWarnings("serial")
public class RoleNotFoundException extends Exception{
	public RoleNotFoundException(String name) {
		super(name);
	}
}
