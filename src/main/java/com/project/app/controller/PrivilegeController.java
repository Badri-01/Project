package com.project.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.exception.privilege.NoPrivilegeFoundException;
import com.project.app.exception.privilege.PrivilegeAlreadyExistsException;
import com.project.app.exception.privilege.RoleAlreadyAssignedException;
import com.project.app.exception.role.RoleNotFoundException;
import com.project.app.exception.user.UserNotFoundException;
import com.project.app.model.Privilege;
import com.project.app.model.Role;
import com.project.app.model.User;
import com.project.app.service.PrivilegeService;

@RequestMapping("/api/v1/privilege/")
@RestController
public class PrivilegeController {
	@Autowired
	private PrivilegeService privilegeService;
	
	
	// Get list of all resources with associated permissions for all roles.
	@PreAuthorize("hasAccess('privilege','read')")
	@GetMapping
	public List<Privilege> getAllPrivileges() {
		return privilegeService.getAllPrivileges();
	}

	// Get permissions of a role for a resource

	@PreAuthorize("hasAccess('privilege','read')")
	@GetMapping("resource/{resourceName}/role/{roleName}")
	public List<String> getPrivilegesOfRole(@PathVariable("resourceName") String resourceName,
			@PathVariable("roleName") String roleName) throws NoPrivilegeFoundException {
		return privilegeService.getPrivilegesOfRole(resourceName, roleName);
	}

	// Get permissions of a resource for all roles
	@PreAuthorize("hasAccess('privilege','read')")
	@GetMapping("resource/{resourceName}/")
	public List<Privilege> getPrivilegesOfResource(@PathVariable("resourceName") String resourceName)
			throws NoPrivilegeFoundException {
		return privilegeService.getPrivilegesOfResource(resourceName);
	}

	// Update the roles of user.
	@PreAuthorize("hasAccess('privilege','update')")
	@PutMapping("{username}/role/")
	public @ResponseBody String updateRole(@PathVariable("username") String username, @Valid @RequestBody Role role)
			throws RoleNotFoundException, RoleAlreadyAssignedException, UserNotFoundException {
		User user = privilegeService.updateRole(username, role);
		if (user != null)
			return "User " + user.getUsername() + " has new role " + role.getRoleName() + " added";
		throw new UserNotFoundException(username);
	}

	// Update permissions of a role for a resource
	@PreAuthorize("hasAccess('privilege','update')")
	@PutMapping("resource-role/")
	public @ResponseBody String updatePrivileges(@Valid @RequestBody Privilege privilege)
			throws PrivilegeAlreadyExistsException {
		Privilege dbprivilege = privilegeService.updatePrivileges(privilege);
		return "Role " + dbprivilege.getRoleName() + " now has " + dbprivilege.getPermissions().toString()
				+ " permissions for " + dbprivilege.getResourceName() + " resource";
	}

	// Define new permissions to a role for all services.

	@PreAuthorize("hasAccess('privilege','write')")
	@PostMapping("role/{roleName}/")
	public @ResponseBody String newPrivilegesForRole(@PathVariable("roleName") String roleName,
			@Valid @RequestBody Privilege privilege) throws PrivilegeAlreadyExistsException {
		privilegeService.newPrivilegesForRole(roleName.toUpperCase(), privilege);
		return "Role " + roleName + " now has " + privilege.getPermissions().toString()
				+ " permissions for all resources";
	}

	// Delete permissions of a role for a resource
	@PreAuthorize("hasAccess('privilege','delete')")
	@DeleteMapping("resource-role/")
	public @ResponseBody String deletePrivileges(@Valid @RequestBody Privilege privilege)
			throws NoPrivilegeFoundException {
		Privilege dbprivilege = privilegeService.deletePrivileges(privilege);
		ArrayList<String> permissions = dbprivilege.getPermissions();
		return "Role " + dbprivilege.getRoleName() + " now doesn't have "
				+ (permissions.size() > 0 ? privilege.getPermissions().toString() : "any") + " permissions for "
				+ dbprivilege.getResourceName() + " resource";
	}
	
	// Remove the role of a user.
		@PreAuthorize("hasAccess('privilege','delete')")
		@DeleteMapping("{username}/role/")
		public @ResponseBody String deleteRole(@PathVariable("username") String username, @Valid @RequestBody Role role)
				throws RoleNotFoundException, UserNotFoundException {
			User user = privilegeService.removeRole(username, role);
			if (user != null)
				return "User " + user.getUsername() + " no longer has role " + role.getRoleName();
			throw new UserNotFoundException(username);
		}

	@ExceptionHandler(PrivilegeAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handlePrivilegeAlreadyExistsException(PrivilegeAlreadyExistsException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(NoPrivilegeFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleNoPrivilegeFoundException(NoPrivilegeFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(RoleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleRoleNotFoundException(RoleNotFoundException ex) {
		return "Role " + ex.getMessage() + " not found";
	}

	@ExceptionHandler(RoleAlreadyAssignedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleRoleAlreadyAssignedException(RoleAlreadyAssignedException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleUserNotFoundException(UserNotFoundException ex) {
		return "Username " + ex.getMessage() + " not found";
	}
}
