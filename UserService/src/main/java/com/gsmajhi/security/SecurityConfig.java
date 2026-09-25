package com.gsmajhi.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final String SECRET= "my-super-secret-key-my-super-secret-key";
	
	@Bean
    public UserDetailsService getUserDetailsService() {
    	CustomUserDetailsService customUserDetailsService=new CustomUserDetailsService();
    	return customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(getUserDetailsService());
    	
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**","/auth").permitAll()
                .requestMatchers("/addUser").permitAll()
                
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth->oauth.jwt(jwt->{}))
            .authenticationProvider(authenticationProvider());

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