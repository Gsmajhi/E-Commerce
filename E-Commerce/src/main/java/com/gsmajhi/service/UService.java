package com.gsmajhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsmajhi.dao.Address;
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
	
	
	public Address saveAddress(Address address) {
		return usInterface.addAddress(address);
	}

	public List<Address> findAddress() {
		
		return usInterface.getAddress();
	}
}
