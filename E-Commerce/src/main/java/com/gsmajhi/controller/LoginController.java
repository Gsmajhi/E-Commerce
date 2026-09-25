package com.gsmajhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsmajhi.dao.LoginRequest;
import com.gsmajhi.dao.LoginResponse;
import com.gsmajhi.dao.User;
import com.gsmajhi.service.PService;
import com.gsmajhi.service.UService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	PService pService;
	
	@Autowired
	UService uService;
	@GetMapping("/loginpage")
	public String showLoginForm() {
		return "loginpage";
	}
	@PostMapping("/login")
	public String login(
	        @RequestParam String username,
	        @RequestParam String password,
	        HttpSession session,
	        Model model) {

	    LoginRequest request = new LoginRequest(username, password);

	    try {
	        LoginResponse response = uService.login(request);

	        if (response == null) {
	            model.addAttribute("error", "Invalid username or password");
	            return "redirect:/loginpage";
	        }

	        session.setAttribute("token", response.getToken());
	        session.setAttribute("username", response.getUsername());
	        session.setAttribute("role", response.getRole());

	        if ("ROLE_USER".equals(response.getRole())) {
	            model.addAttribute("msg", "Welcome " + username);
	            
	            return "redirect:/home";
	        } else {
	            return "sellerdashboard";
	        }

	    } catch (feign.FeignException.Unauthorized e) {
	        model.addAttribute("error", "Invalid username or password");
	        return "redirect:/loginpage";
	    }
	}
	
	 	
	@GetMapping("/registrationpage")
	public String showRegistrationPage(Model m) {
		m.addAttribute("user",new User());
		return "registrationpage";
	}
	
	@PostMapping("/addUser")
	public String createAccount(@ModelAttribute User user, RedirectAttributes redirectAttrs) {
		user.setRole("ROLE_USER");
		User u= uService.createAccount(user);
		if(u!=null) {
			redirectAttrs.addFlashAttribute("msg","Registration Successfull");
		}
		else {
			redirectAttrs.addFlashAttribute("msg","Something went wrong");
		}
		return "redirect:/registrationpage";
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {

	    session.invalidate();

	    return "redirect:/home";
	}
}
