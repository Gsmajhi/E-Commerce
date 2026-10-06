package com.gsmajhi.security;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private final String jwtToken;
	
	public JwtAuthenticationToken(Object principal, @Nullable Object credentials,
			Collection<? extends GrantedAuthority> authorities,String jwtToken) {
		super(principal, credentials, authorities);
		this.jwtToken=jwtToken;
		
	}
	public String getJwtToken() {
		return jwtToken;
	}

}
