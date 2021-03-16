package com.example.demo.exception;

public class RequirementNotFoundexception extends Exception{
	public RequirementNotFoundexception(String reqid) {
		super(reqid);
	}
}
