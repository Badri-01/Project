package com.project.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.app.exception.privilege.NoPrivilegeFoundException;
import com.project.app.exception.privilege.PrivilegeAlreadyExistsException;
import com.project.app.exception.privilege.RoleAlreadyAssignedException;
import com.project.app.exception.role.RoleNotFoundException;
import com.project.app.model.Privilege;
import com.project.app.model.Resource;
import com.project.app.model.Role;
import com.project.app.model.User;
import com.project.app.repository.PrivilegeRepository;
import com.project.app.repository.ResourceRepository;
import com.project.app.repository.RoleRepository;
import com.project.app.repository.UserRepository;

@Service
public class PrivilegeService {
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResourceRepository resourceRepository;

	public List<Privilege> getAllPrivileges() {
		return privilegeRepository.findAll(Sort.by(Sort.Direction.ASC, "roleName"));
	}

	public void addPrivilege(Privilege privilege) {
		privilegeRepository.insert(privilege);
	}

	public User updateRole(String username, @Valid Role role)
			throws RoleNotFoundException, RoleAlreadyAssignedException {
		User user = userRepository.findByUsername(username);
		Set<Role> roles = user.getRoles();
		Role dbrole = roleRepository.findByRoleName(role.getRoleName());
		if (dbrole == null) {
			throw new RoleNotFoundException(role.getRoleName());
		}
		if (roles.contains(dbrole)) {
			throw new RoleAlreadyAssignedException("User " + username + " already has " + role.getRoleName() + " role");
		}
		roles.add(dbrole);
		user.setRoles(roles);
		return userRepository.save(user);
	}

	public void newPrivilegesForRole(String roleName, @Valid Privilege privilege)
			throws PrivilegeAlreadyExistsException {

		List<Resource> resources = resourceRepository.findAll();
		boolean resourceAlreadyExist = false;
		ArrayList<String> alreadyExist = new ArrayList<>();
		ArrayList<Privilege> newPrivileges = new ArrayList<>();
		for (Resource resource : resources) {
			Privilege dbprivilege = privilegeRepository.findByRoleNameAndResourceName(roleName,
					resource.getResourceName());
			if (dbprivilege == null) {
				ArrayList<String> permissions = privilege.getPermissions();
				Privilege newPrivilege = new Privilege(roleName, resource.getResourceName(), permissions);
				newPrivileges.add(newPrivilege);
			} else {
				resourceAlreadyExist = true;
				alreadyExist.add(resource.getResourceName()); // Already there is a privilege with given role for this
																// resource.
			}
		}

		if (resourceAlreadyExist) {
			String buildMessage = "";
			for (int i = 0; i < alreadyExist.size(); i++) {
				buildMessage += alreadyExist.get(i);
				if (i != alreadyExist.size() - 1)
					buildMessage += ", ";
			}
			throw new PrivilegeAlreadyExistsException("Permissions of " + roleName + " role is already set for "
					+ buildMessage
					+ " resource(s). Consider updating permissions of the role to a specific resource through /privilege/resource-role/");
		}
		for (Privilege newPrivilege : newPrivileges)
			privilegeRepository.insert(newPrivilege);
	}

	public List<String> getPrivilegesOfRole(String resourceName, String roleName) throws NoPrivilegeFoundException {
		Privilege dbprivilege = privilegeRepository.findByRoleNameAndResourceName(roleName, resourceName);
		if (dbprivilege != null) {
			return dbprivilege.getPermissions();
		}
		throw new NoPrivilegeFoundException(
				"No privileges of role " + roleName + " exists for the resource " + resourceName);
	}

	public Privilege updatePrivileges(@Valid Privilege privilege) throws PrivilegeAlreadyExistsException {
		Privilege dbPrivilege = privilegeRepository.findByRoleNameAndResourceName(privilege.getRoleName(),
				privilege.getResourceName());
		boolean exactPermissionsAlreadyExist = true;
		ArrayList<String> alreadyExist = new ArrayList<>();
		if (dbPrivilege == null) {
			return privilegeRepository.insert(privilege);
		}
		ArrayList<String> permissions = privilege.getPermissions();
		ArrayList<String> existingPermissions = dbPrivilege.getPermissions();
		for (String permission : permissions) {
			if (!existingPermissions.contains(permission)) {
				exactPermissionsAlreadyExist = false;
				existingPermissions.add(permission);
			} else {
				alreadyExist.add(permission);
			}
		}
		if (exactPermissionsAlreadyExist) {
			throw new PrivilegeAlreadyExistsException("Role " + privilege.getRoleName() + " already has "
					+ alreadyExist.toString() + " permissions for " + privilege.getResourceName() + " resource.");
		}
		dbPrivilege.setPermissions(existingPermissions);
		return privilegeRepository.save(dbPrivilege);
	}

	public Privilege deletePrivileges(@Valid Privilege privilege) throws NoPrivilegeFoundException {
		Privilege dbPrivilege = privilegeRepository.findByRoleNameAndResourceName(privilege.getRoleName().toUpperCase(),
				privilege.getResourceName().toUpperCase());
		if (dbPrivilege == null) {
			throw new NoPrivilegeFoundException("No privileges of role " + privilege.getRoleName()
					+ " exists for the resource " + privilege.getResourceName());
		}
		ArrayList<String> permissions = privilege.getPermissions();
		ArrayList<String> existingPermissions = dbPrivilege.getPermissions();
		for (String permission : permissions) {
			if (existingPermissions.contains(permission)) {
				existingPermissions.remove(permission);
			}
		}
		dbPrivilege.setPermissions(existingPermissions);
		if (existingPermissions.size() == 0) {
			privilegeRepository.delete(dbPrivilege);
		}
		return dbPrivilege;
	}

	public List<Privilege> getPrivilegesOfResource(String resourceName) throws NoPrivilegeFoundException {
		List<Privilege> privilegesOfResource = privilegeRepository.findByResourceName(resourceName.toUpperCase());
		if (privilegesOfResource == null) {
			throw new NoPrivilegeFoundException("No privileges exist for the resource " + resourceName);
		}
		return privilegesOfResource;
	}

	public User removeRole(String username, @Valid Role role) throws RoleNotFoundException {
		User user = userRepository.findByUsername(username);
		Set<Role> roles = user.getRoles();
		Role dbrole = roleRepository.findByRoleName(role.getRoleName().toUpperCase());
		if (dbrole == null) {
			throw new RoleNotFoundException(role.getRoleName());
		}
		if (roles.contains(dbrole)) {
			roles.remove(dbrole);
		} else
			System.out.println("User " + username + " doesn't contain " + role.getRoleName());
		user.setRoles(roles);
		return userRepository.save(user);
	}
}
