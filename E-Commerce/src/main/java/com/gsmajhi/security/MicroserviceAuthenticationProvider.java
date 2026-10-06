package com.gsmajhi.security;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.gsmajhi.dao.LoginRequest;
import com.gsmajhi.dao.LoginResponse;
import com.gsmajhi.service.UService;

import feign.FeignException;

@Component
public class MicroserviceAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UService uService;
	@Override
	public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		
		LoginRequest request=new LoginRequest(username,password);
		
		try {
			LoginResponse response=uService.login(request);
			
			if(response==null || response.getToken()==null) {
				throw new BadCredentialsException("invalid username or password");
				
			}
			
			List<SimpleGrantedAuthority> authorities=List.of(new SimpleGrantedAuthority(response.getRole()));
			
			return new JwtAuthenticationToken(response.getUsername(),null,authorities,response.getToken());
		}catch(FeignException.Unauthorized e) {
			throw new BadCredentialsException("Invalid username or password");
		}catch(Exception e) {
			throw new BadCredentialsException("Authentication faild via userService");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
