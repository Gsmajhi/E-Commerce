package com.gsmajhi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsmajhi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	public User findByName(String name);
	
	Optional<User> findByEmail(String email);
	
		
}
