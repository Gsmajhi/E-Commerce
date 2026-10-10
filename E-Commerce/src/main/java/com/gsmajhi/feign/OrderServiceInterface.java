package com.gsmajhi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gsmajhi.dao.Order;
import com.gsmajhi.dao.OrderItem;


@FeignClient("OrderService")
public interface OrderServiceInterface {
	@PostMapping("/checkout")
	public OrderItem createOrder(@RequestBody OrderItem orderItem);
	
	@GetMapping("/myorders")
	public List<Order> ordersByUserId();
	
	@GetMapping("/orders")
	public List<OrderItem> getOrders();
	
	
	@PutMapping("/checkout/{orderId}/{addressId}")
	public Order addAddressToOrder(@PathVariable String orderId,@PathVariable String addressId);


	@PutMapping("/place_order/{orderId}/{paymentId}")
	public Order addPaymentIdToOrder(@PathVariable String orderId,@PathVariable String paymentId);
	
}
