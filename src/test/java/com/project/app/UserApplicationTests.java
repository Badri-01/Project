package com.project.app;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Date;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.app.model.User;
import com.project.app.repository.UserRepository;
import com.project.app.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {
	
	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;
	
	
	@TestConfiguration
	static class UserServiceImplTestContextConfiguration{
		@Bean
		public UserService service() {
			return new UserService();
		}
	}

	@Test
	public void getUsersTest() {
		when(userRepository.findAll()).thenReturn(Stream
				.of(new User("Badri01","Badrinadh Reddy","Dayala","1badrikbn@gmail.com","9182427915","abcdef",new Date(),new Date()),
					new User("theAdmin","Admin","One","adminMail@gmail.com","9900041235","admin",new Date(),new Date()))
				.collect(Collectors.toList()));
		System.out.println(userService.getAllUsers().toString());
		assertEquals(2, userService.getAllUsers().size());
	}
	
	@Test
	public void getUserByUserNameTest() throws ParseException {
		String username="Badri01";
		User user =new User("Badri01","Badrinadh Reddy","Dayala","1badrikbn@gmail.com","9182427915","abcdef",new Date(),new Date());
		when(userRepository.findByUsername(username)).thenReturn(user);
		//System.out.println(user.equals(userRepository.findByUsername(username)));
		assertEquals(user, userService.getByUsername(username));
	}
	
	@Test
	public void registerNewUserTest() throws ParseException {
		User user =new User("Badri01","Badrinadh Reddy","Dayala","1badrikbn@gmail.com","9182427915","abcdef",new Date(),new Date());
		when(userRepository.insert(user)).thenReturn(user);
		assertEquals(user, userService.addUser(user));
	}
}
