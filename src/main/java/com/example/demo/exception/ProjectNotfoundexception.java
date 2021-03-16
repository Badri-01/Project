package com.example.demo.exception;

public class ProjectNotfoundexception extends Exception{

	public ProjectNotfoundexception(String projectid) {
		super(projectid);
	}
}
