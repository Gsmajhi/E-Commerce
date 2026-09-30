package com.gsmajhi.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Component
public class FeignJwtInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		ServletRequestAttributes attributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(attributes==null) {
			System.out.println("attributes is null");
			return;
		}
		HttpServletRequest request=attributes.getRequest();
		
		
		String authorization=request.getHeader("Authorization");
		
		System.out.println("authorization received"+authorization);
		if(authorization!=null && !authorization.isBlank()) {
			template.header("Authorization", authorization);;
		}
		
	}

}
