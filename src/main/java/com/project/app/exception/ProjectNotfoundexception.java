package com.project.app.exception;

public class ProjectNotfoundexception extends Exception{

	public ProjectNotfoundexception(String projectid) {
		super(projectid);
	}
}
