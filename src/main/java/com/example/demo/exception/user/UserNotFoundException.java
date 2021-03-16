package com.example.demo.exception.user;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception{
	public UserNotFoundException(String username) {
		super(username);
	}
}
