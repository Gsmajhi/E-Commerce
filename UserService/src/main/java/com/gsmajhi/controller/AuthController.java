package com.gsmajhi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsmajhi.dto.LoginRequest;
import com.gsmajhi.dto.LoginResponse;
import com.gsmajhi.security.CustomUserDetails;
import com.gsmajhi.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public AuthController(AuthenticationManager authenticationManager,
			JwtService jwtService) {
		this.authenticationManager=authenticationManager;
		this.jwtService=jwtService;
	}
	  @GetMapping("/test")
	    public String test() {
	        return "Auth controller working";
	    }

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		CustomUserDetails user=(CustomUserDetails)authentication.getPrincipal();
		String role=user.getAuthorities().iterator().next().getAuthority();
	
		String token=jwtService.generateToken(authentication);
		LoginResponse response=new LoginResponse(token,user.getUsername(),role);
		
		return ResponseEntity.ok(response);
	}
	
}
