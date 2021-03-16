package com.example.demo.exception.privilege;

@SuppressWarnings("serial")
public class NoPrivilegeFoundException extends Exception{

	public NoPrivilegeFoundException(String message) {
		super(message);
	}
	
}
