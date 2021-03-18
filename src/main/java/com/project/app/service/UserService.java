package com.project.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.app.dto.UserDto;
import com.project.app.model.User;
import com.project.app.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder bcryptEncoder;
	
	public void addUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		userRepository.insert(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User removeUser(String username) {
		Query q=new Query();
		q.addCriteria(Criteria.where("username").is(username));
		User user=userRepository.findByUsername(username);
		if(user==null)
			return null;
		user.setStatus("Inactive");
		userRepository.save(user);
		return user;
	}

	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User updateUser(String username, UserDto userDto) {
		User oldUser = userRepository.findByUsername(username);
		if(oldUser==null)
			return null;
		User user=userDto.toUser();
		if(user.getPassword()!=null)
		     user.setPassword(bcryptEncoder.encode(user.getPassword()));
		oldUser.update(user);
		userRepository.save(oldUser);
		return oldUser;
	}
}
