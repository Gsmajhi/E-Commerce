package com.gsmajhi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gsmajhi.dao.Order;
import com.gsmajhi.dao.OrderItem;


@FeignClient("OrderService")
public interface OrderServiceInterface {
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody OrderItem orderItem);
	
	@GetMapping("/myorders")
	public List<Order> ordersByUserId();
	
	@GetMapping("/orders")
	public List<OrderItem> getOrders();
}
