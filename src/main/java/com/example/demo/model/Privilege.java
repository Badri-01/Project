package com.example.demo.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "privileges")
public class Privilege {
	@Id
	private String privilegeId;
    private ArrayList<String> permissions=new ArrayList<String>(); //READ, WRITE,UPDATE, DELETE
	private String resourceName;
	private String roleName;

	public Privilege(String roleName, String resourceName, ArrayList<String> permissions) {
	    this.permissions=permissions;
		this.resourceName=resourceName;
		this.roleName=roleName;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setPermissions(ArrayList<String> permissions) {
		this.permissions = permissions;
	}
	
	
	
}
