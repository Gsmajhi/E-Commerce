package com.example.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final String SECRET= "my-super-secret-key-my-super-secret-key";
	
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter=new JwtAuthenticationConverter();
		 converter.setJwtGrantedAuthoritiesConverter(new JwtRoleConverter());
		 
		 return converter;
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers("/products").authenticated()
				.requestMatchers("/allproducts",
						"/products/{productId}",
						"/images/{fileName}",
						"/updateproduct/{productId}",
						"/category/{categoryName}").permitAll()
				.requestMatchers("/productbyseller").hasRole("SELLER")
				.anyRequest().authenticated())
		
		.oauth2ResourceServer(oauth2->oauth2.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
		
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
