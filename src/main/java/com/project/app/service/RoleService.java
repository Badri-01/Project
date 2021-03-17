package com.project.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.app.model.Role;
import com.project.app.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void addRole(Role role) {
		role.setRoleStatus("Inactive");
		roleRepository.insert(role);
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	public Role removeRole(String roleName) {
		Role role = roleRepository.findByRoleName(roleName);
		if(role==null)
			return null;
		roleRepository.delete(role);
		return role;
	}

	public Role getByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}
}

