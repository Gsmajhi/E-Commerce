package com.gsmajhi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final MicroserviceAuthenticationProvider authProvider;
	private final SuccessHandler successHandler;
    
	public SecurityConfig(MicroserviceAuthenticationProvider authProvider,SuccessHandler successHandler) {
		this.authProvider=authProvider;
				this.successHandler=successHandler;
	}
	
	@Bean
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable())
		.authenticationProvider(authProvider)
		.authorizeHttpRequests(auth->auth.requestMatchers("/loginpage", "/css/**",
                "/js/**",
                "/images/**").permitAll()
				.requestMatchers("/gotocart","/checkout","/addToCart/**").authenticated().anyRequest().permitAll()
				)
		.formLogin(login->login.loginPage("/loginpage")
				.loginProcessingUrl("/login")
				.successHandler(successHandler).permitAll());
		
		
		
		
		
		return http.build();
	}
	
	

}
