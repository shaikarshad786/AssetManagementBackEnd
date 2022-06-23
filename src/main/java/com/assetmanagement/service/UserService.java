package com.assetmanagement.service;

import java.util.List;

import com.assetmanagement.entity.Asset;
import com.assetmanagement.entity.Shipment;
import com.assetmanagement.entity.User;

public interface UserService {

	public User doLogin(String userName, String password);

	public User saveUser(User user);

	public List<User> getAllUser();
	
	public List<User> getAllUserByPosition(String userPosition);

	public User getUserById(int userId);

	public User updateUser(User user);

	public void deleteUserById(int userId);
	
}
