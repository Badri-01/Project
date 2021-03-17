package com.project.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.exception.role.RoleAlreadyExistsException;
import com.project.app.exception.role.RoleNotFoundException;
import com.project.app.model.Role;
import com.project.app.service.RoleService;

@RequestMapping("/api/v1/role/")
@RestController	
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@PreAuthorize("hasAccess('role','read')")
	@GetMapping
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}
	
	@PreAuthorize("hasAccess('role','read')")
	@GetMapping(path = "{roleName}")
	public Role getByRoleName(@PathVariable("roleName") String roleName) throws RoleNotFoundException{
		Role role=roleService.getByRoleName(roleName);
		if(role!=null)
			return role;
		throw new RoleNotFoundException(roleName);
	}
	
	@PreAuthorize("hasAccess('role','write')")
	@PostMapping
	public @ResponseBody String createRole(@Valid @RequestBody Role role) throws RoleAlreadyExistsException{
		try {
			roleService.addRole(role);
		}
		catch(DuplicateKeyException ex) {
			//System.out.println(ex.getClass());
			throw new RoleAlreadyExistsException(role.getRoleName());
		}
		return role.getRoleName()+" role created";
	}
	
	@PreAuthorize("hasAccess('role','delete')")
	@DeleteMapping(path = "{roleName}")
	public String deleteRole(@PathVariable("roleName") String roleName) throws RoleNotFoundException{
		Role role=roleService.removeRole(roleName);
		if(role!=null)
			return "Role "+role.getRoleName()+" deleted";
		throw new RoleNotFoundException(roleName);
	}
	
	@ExceptionHandler(RoleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleRoleNotFoundException(RoleNotFoundException ex)
	{
	  return "Role "+ex.getMessage()+" not found";
	}
	
	@ExceptionHandler(RoleAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleRoleAlreadyExistsException(RoleAlreadyExistsException ex)
	{
	  return "Role "+ ex.getMessage()+" already exists";
	}
	
}
