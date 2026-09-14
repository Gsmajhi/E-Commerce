package com.gsmajhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsmajhi.entity.User;
import com.gsmajhi.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userRepo.save(user);
		
	}
	
	
	
	@PostMapping("/login")
	public String login(@RequestParam String name,@RequestParam String password) {
		User user=userRepo.findByName(name);
		
		if(user!=null) {
			String pass=user.getPassword();
			if(pass.equals(password)) {
				return "welcome "+name;
			}else {
				return "Incorrect Password";
			}
		}
		else {
			return "user not exists";
		}

	}

}
