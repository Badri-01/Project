package com.example.demo.model;

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
	
	
	/*public User(String username, String firstName, String lastName,@NotNull String email, String mobile, String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
	}*/

	public User() {
		//do nothing
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

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void update(User user) {
		this.firstName=user.firstName;
		this.lastName=user.lastName;
		this.email=user.email;
		this.password=user.password;
		this.mobile=user.mobile;
	}

	public void setPassword(String encode) {
		this.password=encode;
	}
	
}
