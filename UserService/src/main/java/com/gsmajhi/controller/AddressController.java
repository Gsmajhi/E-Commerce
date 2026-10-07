package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsmajhi.entity.Address;
import com.gsmajhi.service.ServiceImp;

@RestController
public class AddressController {
	
	@Autowired
	ServiceImp serviceImp;
	@PostMapping("/addAddress")
	public Address addAddress(@RequestBody Address address,@AuthenticationPrincipal Jwt jwt) {
		address.setUserId(jwt.getSubject());
		return serviceImp.saveAddress(address);
	}

	@GetMapping("/addresses")
	public List<Address> getAddress(@AuthenticationPrincipal Jwt jwt){
		return serviceImp.findAddress(jwt.getSubject());
	}
	
	
	
}
