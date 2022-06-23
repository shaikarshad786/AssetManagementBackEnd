package com.assetmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assetmanagement.entity.User;
import com.assetmanagement.model.LoginRequest;
import com.assetmanagement.model.LoginResponse;
import com.assetmanagement.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user/save")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {

		User newUser = userService.saveUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(newUser, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/user/all")
	public List<User> fetchAllUser() {
		List<User> allUser = userService.getAllUser();
		return allUser;
	}

	@GetMapping("/user/find/{userId}")
	public ResponseEntity<Object> fetchById(@PathVariable("userId") int userId) {

		ResponseEntity<Object> reponseEntity = null;
		User user = userService.getUserById(userId);
		reponseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return reponseEntity;
	}

	@PostMapping("/user/login")
	public ResponseEntity<LoginResponse> signin(@Valid@RequestBody LoginRequest loginRequest) {

		User user = userService.doLogin(loginRequest.getUserName(), loginRequest.getPassword());

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUserId(user.getUserId());
		loginResponse.setUserName(user.getUserName());
		loginResponse.setUserMobile(user.getMobileNumber());
		loginResponse.setUserAddress(user.getAddress());
		loginResponse.setUserEmail(user.getEmailId());
		loginResponse.setUserPosition(user.getUserPosition());

		ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("user/update")
	public ResponseEntity<User> updateUser(@Valid@RequestBody User user) {

		User updatedUser = userService.updateUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(updatedUser, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/user/delete/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable int userId) {

		userService.deleteUserById(userId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/user/byPosition/{userPosition}")
	public List<User> fectUserByPosition(@PathVariable("userPosition") String userPosition) {

		List<User> userByPosition = userService.getAllUserByPosition(userPosition);
		return userByPosition;
	}
}
