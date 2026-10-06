package com.gsmajhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsmajhi.entity.User;
import com.gsmajhi.repository.UserRepository;
import com.gsmajhi.service.ServiceImp;

@RestController
public class UserController {
	
	@Autowired
	ServiceImp serviceImp;
	
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return serviceImp.saveUser(user);
		
	}
	

	
	@GetMapping("/hello")
	public String hello() {
		return "hello from userController";
	}
	
	@GetMapping("/loggedInuser")
	public User getLoggedInUser(
	        @AuthenticationPrincipal Jwt jwt) {

	    String userId = jwt.getSubject();

	    return userRepo.findById(userId).orElse(null);
	}
//	@PostMapping("/login")
//	public String login(@RequestParam String name,@RequestParam String password) {
//		User user=userRepo.findByName(name);
//		
//		if(user!=null) {
//			String pass=user.getPassword();
//			if(pass.equals(password)) {
//				return "welcome "+name;
//			}else {
//				return "Incorrect Password";
//			}
//		}
//		else {
//			return "user not exists";
//		}
//
//	}

}
