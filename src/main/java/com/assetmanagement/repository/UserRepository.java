package com.assetmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assetmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{


	public List<User> findByUserPosition(String userPosition);
	
	@Query("select u from User u where u.userName = :uname and u.password = :upwd")
	User login(@Param("uname") String username, @Param("upwd") String password);

}
