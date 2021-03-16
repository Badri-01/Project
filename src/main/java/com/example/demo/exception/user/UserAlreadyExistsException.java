package com.example.demo.exception.user;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException(String username) {
		super(username);
	}
	
}
