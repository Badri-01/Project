package com.project.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String userId;
	@NotNull
	@Indexed(unique=true)
	private String username;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String mobile;
	@NotNull
	private String password;
	@CreatedDate
    private Date createdDate;
	@LastModifiedDate
	private Date lastModifiedDate;
	@DBRef
	private Set<Role> roles=new HashSet<Role>();;
	private String status="Active";
	
	
	/*public User(String username, String firstName, String lastName,@NotNull String email, String mobile, String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
	}*/

	public User(String firstName, String lastName, String password, String email, String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public String getPassword() {
		return password;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void update(User user) {
		if(user.firstName!=null)
		    this.firstName=user.firstName;
		if(user.lastName!=null)
	  	    this.lastName=user.lastName;
		if(user.email!=null)
			this.email=user.email;
		if(user.password!=null)
			this.password=user.password;
		if(user.mobile!=null)
			this.mobile=user.mobile;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPassword(String encode) {
		this.password=encode;
	}
	
}
