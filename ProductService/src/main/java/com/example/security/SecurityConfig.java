package com.example.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final String SECRET= "my-super-secret-key-my-super-secret-key";
	
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers("/products").authenticated()
				.requestMatchers("/allproducts","/products/{productId}").permitAll().anyRequest().authenticated())
		
		.oauth2ResourceServer(oauth2->oauth2.jwt(jwt->{}));
		
		return http.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		 SecretKey key = Keys.hmacShaKeyFor(
		            SECRET.getBytes(StandardCharsets.UTF_8)
		        );
		
		return NimbusJwtDecoder.withSecretKey(key).build();
	}

}
