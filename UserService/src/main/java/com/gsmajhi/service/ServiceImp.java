package com.gsmajhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gsmajhi.entity.User;
import com.gsmajhi.repository.UserRepository;


@Service
public class ServiceImp  {
	
	@Autowired
	UserRepository userRepo;
	
	public User saveUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepo.save(user);
	}

}
