package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "roles")
public class Role {
	@Id
	private String roleId;
	@Indexed(unique=true)
	private String roleName;
	
	private String roleStatus;
	
	public Role(@JsonProperty("roleName")String roleName) {
		this.roleName = roleName;
	}
	public String getRoleId() {
		return roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String status) {
		this.roleStatus=status;
	}
	
	//equals method and hashCode must be overridden to ensure equality while using List or HashSet of objects.
	@Override
	public boolean equals(Object obj) {
		//System.out.println("equals method got called");
		if(obj instanceof Role) {
		     Role newRole=(Role) obj;
		     if(newRole.getRoleId()==null) {
		    	 return newRole.getRoleName().equals(this.roleName);
		     }
		     else if(newRole.getRoleId().equals(this.roleId)) {
		    	 //System.out.println("equal");
		    	 return true;
		     }
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int idHash=0, nameHash=0,statusHash=0;
		if(roleId!=null)
		   idHash= 3*roleId.hashCode();
		if(roleName!=null)
		   nameHash = 7*roleName.hashCode();
		if(roleStatus!=null)
			statusHash=10*roleStatus.hashCode();
		return idHash+nameHash+statusHash;
	}
	
}
