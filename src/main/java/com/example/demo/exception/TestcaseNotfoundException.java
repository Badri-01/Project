package com.example.demo.exception;

public class TestcaseNotfoundException extends Exception {
	public TestcaseNotfoundException(String testcaseid) {
		super(testcaseid);
	}
}
