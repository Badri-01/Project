package com.project.app.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "roles")
public class Role {
	@Id
	private String roleId;
	@Indexed(unique=true)
	private String roleName;
	@CreatedDate
    private Date createdDate;
	@LastModifiedDate
	private Date lastModifiedDate;
	//@LastModifiedBy
	//private String lastModifiedBy=SecurityContextHolder.getContext().getAuthentication().getName();
	private String roleStatus="Active";
	
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
		//lastModifiedBy=SecurityContextHolder.getContext().getAuthentication().getName();
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
