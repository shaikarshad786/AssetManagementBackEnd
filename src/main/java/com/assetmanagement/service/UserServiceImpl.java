package com.assetmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmanagement.entity.User;
import com.assetmanagement.exception.AuthenticationFailedException;
import com.assetmanagement.exception.UserNotFoundException;
import com.assetmanagement.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;

	@Override
	public User doLogin(String userName, String password) {

		User user = userRepository.login(userName, password);
		if (user == null) {
			throw new AuthenticationFailedException("Username and password are invalid");
		}
		return user;
	}

	@Override
	public User saveUser(User user) {

		User newUser = userRepository.save(user);
		return newUser;
	}

	@Override
	public List<User> getAllUser() {

		List<User> allUser = userRepository.findAll();
		return allUser;
	}

	@Override
	public User getUserById(int userId) throws UserNotFoundException {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User not found with this Id :" + userId);
		}
		User userById = optionalUser.get();
		return userById;
	}

	@Override
	public User updateUser(User user) {

		Optional<User> optionalUser = userRepository.findById(user.getUserId());
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User not found with this Id :" + user.getUserId());
		}
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	@Override
	public void deleteUserById(int userId) {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User not found with this Id :" + userId);
		}

		userRepository.deleteById(userId);
	}

	@Override
	public List<User> getAllUserByPosition(String userPosition) {
		
        List<User> userByPosition = userRepository.findByUserPosition(userPosition);
        if(userByPosition.size() == 0) {
        	throw new UserNotFoundException("User not found with this name : " + userPosition);
        }
		return userByPosition;
	}	
}
