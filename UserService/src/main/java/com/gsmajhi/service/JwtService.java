package com.gsmajhi.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.gsmajhi.security.CustomUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component

public class JwtService {
	private final String SECRET="my-super-secret-key-my-super-secret-key";
	
	public String generateToken(Authentication authentication) {
		CustomUserDetails userDetails=(CustomUserDetails)authentication.getPrincipal();
		
		String role=userDetails.getAuthorities().iterator().next().getAuthority();
		
		return Jwts.builder().subject(String.valueOf(userDetails.getId()))
				.claim("roles", role)
				.claim("username", userDetails.getUsername())
				.setIssuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(getKey()).compact();
	}
	
	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	}

}
