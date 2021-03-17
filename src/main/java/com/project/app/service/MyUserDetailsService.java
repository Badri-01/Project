package com.project.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.app.model.Privilege;
import com.project.app.model.User;
import com.project.app.repository.PrivilegeRepository;
import com.project.app.repository.UserRepository;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        //System.out.println(getAuthority(user));
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}
	
//	public ArrayList<String> getRoles(User user) {
//        ArrayList<String> roles=new ArrayList<>();
//        user.getRoles().forEach(role -> {roles.add(role.getRoleName());});
//        return roles;
//	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
        	ArrayList<Privilege> privileges = privilegeRepository.findByRoleName(role.getRoleName());
        	for(Privilege privilege:privileges) {
        		for(String permission:privilege.getPermissions()) {
        			authorities.add(new SimpleGrantedAuthority(privilege.getResourceName()+"_"+permission));
        		}
        	}
        });
        return authorities;
    }
}


