package com.gsmajhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsmajhi.dao.LoginRequest;
import com.gsmajhi.dao.LoginResponse;
import com.gsmajhi.dao.User;
import com.gsmajhi.feign.UserServiceInterface;

@Service
public class UService {
	
	@Autowired
	UserServiceInterface usInterface;

	public String login(String name,String password) {
		return usInterface.login(name, password);
	}
	
	public User createAccount(User user) {
		return usInterface.createAccount(user);
	}
	
	public LoginResponse login(LoginRequest request) {
		return usInterface.login(request);
	}
	
	public User getLoggedInUser() {
		return usInterface.getLoggedInUser();
	}
	
	
}
