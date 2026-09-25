package com.gsmajhi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gsmajhi.dao.Order;


@FeignClient("OrderService")
public interface OrderServiceInterface {
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody Order order);
	
	@GetMapping("/orders")
	public List<Order> ordersByUserId();
}
