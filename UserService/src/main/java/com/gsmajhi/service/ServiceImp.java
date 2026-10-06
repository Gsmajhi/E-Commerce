package com.gsmajhi.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gsmajhi.entity.Address;
import com.gsmajhi.entity.User;
import com.gsmajhi.repository.AddressRepository;
import com.gsmajhi.repository.UserRepository;


@Service
public class ServiceImp  {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AddressRepository addressRepo;
	
	public User saveUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepo.save(user);
	}

	public Address saveAddress(Address address) {
		
		return addressRepo.save(address);
	}

	public List<Address> findAddress( String userId) {
		return addressRepo.findAddressByUserId(userId);
		
	}
}
