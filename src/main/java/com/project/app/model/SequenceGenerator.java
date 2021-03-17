package com.project.app.model;


public class SequenceGenerator {
	
	public static String generateID(String lastitem) {
		String []arr=lastitem.split("-");
		int id=Integer.parseInt(arr[arr.length-1])+1;
		return arr[arr.length-2]+'-'+id;
		
}
	}

