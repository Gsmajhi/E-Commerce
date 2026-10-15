package com.example.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtRoleConverter implements Converter<Jwt,Collection<GrantedAuthority>>{

	

	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {
		List<String> roles=source.getClaimAsStringList("roles");
		
		if(roles==null) {
			return List.of();
		}
		return roles.stream().map(role->role.startsWith("ROLE_")
				?role:"ROLE_"+role)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

}
