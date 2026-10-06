package com.gsmajhi.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
		HttpSession session = request.getSession();

		if (authentication instanceof JwtAuthenticationToken jwtAuth) {
			session.setAttribute("token", jwtAuth.getJwtToken());
			session.setAttribute("username", jwtAuth.getName());
		}

		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			response.sendRedirect(targetUrl);
			System.out.println(targetUrl);
			return;

		}else {
			System.out.println("target url is null");
		}

		boolean isUser = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"));

		if (isUser) {
			response.sendRedirect("/home");
		} else {
			response.sendRedirect("/sellerdashboard");
		}
	}
}
