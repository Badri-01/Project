package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

import com.example.demo.exception.user.UserAlreadyExistsException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RequestMapping("/api/v1/user/")
@RestController	
public class UserController {
	
	User dummyObject=new User();
	
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAccess('user','read')")
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PreAuthorize("hasAccess('user','read')")
	@GetMapping(path = "{username}")
	public User getByUsername(@PathVariable("username") String username) throws UserNotFoundException{
		User user=userService.getByUsername(username);
		if(user!=null)
			return user;
		
		throw new UserNotFoundException(username);
	}
	
	@PostMapping("register/")
	public @ResponseBody String registerNewUUser(@Valid @RequestBody User user) throws UserAlreadyExistsException, MethodArgumentNotValidException{
		try {
			userService.addUser(user);
		}
		catch(DuplicateKeyException ex) {
			//System.out.println(ex.getClass());
			throw new UserAlreadyExistsException(user.getUsername());
		}
		return "Signed up succesfully";
	}
	
	@PreAuthorize("hasAccess('user','update')")
	@PutMapping(path = "{username}")
	public @ResponseBody String updateUser(@PathVariable("username") String username, @Valid @RequestBody User user) throws UserNotFoundException{
		user=userService.updateUser(username,user);
		if(user!=null)
			return "User "+user.getUsername()+" account updated";
		throw new UserNotFoundException(username);
	}
	
	
	@PreAuthorize("hasAccess('user','delete')")
	@DeleteMapping(path = "{username}")
	public String deleteUser(@PathVariable("username") String username) throws UserNotFoundException{
		User user=userService.removeUser(username);
		if(user!=null)
			return "User "+user.getUsername()+" account deleted";
		throw new UserNotFoundException(username);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleUserNotFoundException(UserNotFoundException ex)
	{
	  return "Username "+ex.getMessage()+" not found";
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleUserAlreadyExistsException(UserAlreadyExistsException ex)
	{
	  return "Username "+ ex.getMessage()+" already exists";
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleValidationException(MethodArgumentNotValidException ex)
	{
		String field=ex.getFieldError().getField();
		return field+" field "+ex.getFieldError(field).getDefaultMessage();
	}
	
	
}
