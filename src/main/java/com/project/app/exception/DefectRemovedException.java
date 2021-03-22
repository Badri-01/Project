package com.project.app.exception;

public class DefectRemovedException extends Exception {
	private static final long serialVersionUID = 1L;

	public DefectRemovedException(String defectId) {
		super(defectId);
	}
}
