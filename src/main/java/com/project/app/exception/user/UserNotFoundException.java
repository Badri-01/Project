package com.project.app.exception.user;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception{
	public UserNotFoundException(String username) {
		super(username);
	}
}
