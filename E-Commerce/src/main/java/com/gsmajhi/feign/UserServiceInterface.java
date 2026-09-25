package com.gsmajhi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsmajhi.dao.LoginRequest;
import com.gsmajhi.dao.LoginResponse;
import com.gsmajhi.dao.User;

@FeignClient("UserService")
public interface UserServiceInterface {
	@PostMapping("/login")
	public String login(@RequestParam String name,@RequestParam String password) ;
	
	@PostMapping("/addUser")
	public User createAccount(User user);
	 @PostMapping("/auth/login")
	    LoginResponse login(
	            @RequestBody LoginRequest request);
	 
	 @GetMapping("/loggedInuser")
		public User getLoggedInUser();
}
