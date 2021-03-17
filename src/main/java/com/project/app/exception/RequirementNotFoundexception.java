package com.project.app.exception;

public class RequirementNotFoundexception extends Exception{
	public RequirementNotFoundexception(String reqid) {
		super(reqid);
	}
}
