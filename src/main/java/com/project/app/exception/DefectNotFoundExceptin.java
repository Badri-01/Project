package com.project.app.exception;

public class DefectNotFoundExceptin extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefectNotFoundExceptin(String defectId) {
		super(defectId);
	}
}
