package com.assetmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.assetmanagement.entity.User;
import com.assetmanagement.exception.AuthenticationFailedException;
import com.assetmanagement.exception.UserNotFoundException;
import com.assetmanagement.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void testGetUserById() {
	
	    User user = new User();
		user.setUserId(10);
		user.setUserName("Ram");
		user.setMobileNumber(900900909);
		user.setEmailId("ram@gmail.com");
		user.setAddress("Hyderabad");
		user.setPassword("ram123");
		user.setUserPosition("Manager");
		
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(10)).thenReturn(optionalUser); 
		
		
		User myUser=userService.getUserById(10);
		assertEquals("Ram",myUser.getUserName());
    }
	@Test
	public void testGetUserByIdWithException() {
		when(userRepository.findById(10)).thenThrow(UserNotFoundException.class);
		
		assertThrows(UserNotFoundException.class,()->userService.getUserById(10));
	}
	
	public void testGetAllUserByPosition() {
		
        User user = new User();
		
		user.setUserId(10);
		user.setUserName("Ram");
		user.setMobileNumber(900900909);
		user.setEmailId("ram@gmail.com");
		user.setAddress("Hyderabad");
		user.setPassword("ram123");
		user.setUserPosition("Warehousr Manager");

		User user1 = new User();

		user1.setUserId(11);
		user1.setUserName("Sai");
		user1.setMobileNumber(888888666);
		user1.setEmailId("sai@gmail.com");
		user1.setAddress("chennai");
		user1.setPassword("sai123");
		user1.setUserPosition("General User");

		User user2 = new User();

		user2.setUserId(12);
		user2.setUserName("Krishna");
		user2.setMobileNumber(777800000);
		user2.setEmailId("krishna@gmail.com");
		user2.setAddress("Mubai");
		user2.setPassword("krishna123");
		user2.setUserPosition("Administrator");

		List<User> userList = new ArrayList<>();
		userList.add(user2);
		userList.add(user1);
		userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);
		
		List<User> users = userService.getAllUserByPosition("General User");
		
		assertEquals(3,users.size());

		
	}
	
	@Test
	public void testGetAllUser() {
		
		User user = new User();
		
		user.setUserId(10);
		user.setUserName("Ram");
		user.setMobileNumber(900900909);
		user.setEmailId("ram@gmail.com");
		user.setAddress("Hyderabad");
		user.setPassword("ram123");
		user.setUserPosition("Warehousr Manager");

		User user1 = new User();

		user1.setUserId(11);
		user1.setUserName("Sai");
		user1.setMobileNumber(888888666);
		user1.setEmailId("sai@gmail.com");
		user1.setAddress("chennai");
		user1.setPassword("sai123");
		user1.setUserPosition("General User");

		User user2 = new User();

		user2.setUserId(12);
		user2.setUserName("Krishna");
		user2.setMobileNumber(777800000);
		user2.setEmailId("krishna@gmail.com");
		user2.setAddress("Mubai");
		user2.setPassword("krishna123");
		user2.setUserPosition("Administrator");

		List<User> userList = new ArrayList<>();
		userList.add(user2);
		userList.add(user1);
		userList.add(user);
		
		when(userRepository.findAll()).thenReturn(userList);
		
		List<User> users = userService.getAllUser();
		
		assertEquals(3,users.size());
		
	}
	
	
	
	
	@Test
	public void testSaveUser() {
		
        User user = new User();
		
		user.setUserId(10);
		user.setUserName("Ram");
		user.setMobileNumber(900900909);
		user.setEmailId("ram@gmail.com");
		user.setAddress("Hyderabad");
		user.setPassword("ram123");
		user.setUserPosition("Warehousr Manager");
		
		when(userRepository.save(user)).thenReturn(user);
		
		User newUser = userService.saveUser(user);
		
		assertEquals("Ram",newUser.getUserName());
		
		verify(userRepository,times(1)).save(user);
	}
	
	@Test
	public void DeleteUserById() {
		User user = new User();
		user.setUserId(10);
		user.setUserName("Ram");
		user.setMobileNumber(900900909);
		user.setEmailId("ram@gmail.com");
		user.setAddress("Hyderabad");
		user.setPassword("ram123");
		user.setUserPosition("Manager");
		
		Optional<User> optionalUser = Optional.of(user);
		
		when(userRepository.findById(10)).thenReturn(optionalUser); 
		
		userService.deleteUserById(10);
		verify(userRepository,times(1)).findById(10);
		verify(userRepository,times(1)).deleteById(10);	

	}
	
	
	@Test
	public void testUpdateUser() {
		
		User user = new User();
		user.setUserId(10);
		user.setUserName("sri");
		user.setMobileNumber(90090099);
		user.setEmailId("sri@gmail.com");
		user.setAddress("Hyderabad");
		user.setPassword("sri123");
		user.setUserPosition("Manager");

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(10)).thenReturn(optionalUser);
		
		userService.updateUser(user);
		
		verify(userRepository, times(1)).findById(10);
		verify(userRepository, times(1)).save(user);
	}
	
	
	@Test
	void testDoLogin() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("Ram");
		user.setPassword("ram123");
		when(userRepository.login(user.getUserName(), user.getPassword())).thenReturn(user);
		assertEquals("Ram", user.getUserName());
		assertEquals("ram123", user.getPassword());
	}
	
	@Test
	void testDoLoginException() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("Ram");
		user.setPassword("ram123");
		when(userRepository.login(user.getUserName(), user.getPassword())).thenThrow(AuthenticationFailedException.class);
		assertThrows(AuthenticationFailedException.class,()->userService.doLogin(user.getUserName(), user.getPassword()));
	}	
	
}