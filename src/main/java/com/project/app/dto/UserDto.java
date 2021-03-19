package com.project.app.dto;

import java.util.Set;

import com.project.app.model.Role;
import com.project.app.model.User;

public class UserDto {

	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String password;
	private Set<Role> roles;
	
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
	
	public User toUser() {
		return new User(firstName,lastName,password,email,mobile);
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void setPassword(String encode) {
		this.password=encode;
	}
	
}
