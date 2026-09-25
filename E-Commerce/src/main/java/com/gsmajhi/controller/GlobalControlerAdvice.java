package com.gsmajhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gsmajhi.dao.User;
import com.gsmajhi.service.UService;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControlerAdvice {
	
	@Autowired
	UService uService;
	@ModelAttribute("currentUser")
	public User getLoggedInUser(HttpSession session) {
		String token=(String) session.getAttribute("token");
		if(token ==null) return null;
		User user=uService.getLoggedInUser();
		System.out.println(user.getRole());
		return user;
	}

}
