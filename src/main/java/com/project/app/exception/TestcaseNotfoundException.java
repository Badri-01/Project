package com.project.app.exception;

public class TestcaseNotfoundException extends Exception {
	public TestcaseNotfoundException(String testcaseid) {
		super(testcaseid);
	}
}
