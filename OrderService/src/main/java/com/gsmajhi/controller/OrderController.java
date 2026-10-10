package com.gsmajhi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsmajhi.entity.Order;
import com.gsmajhi.entity.OrderItem;
import com.gsmajhi.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	
	
	@GetMapping("/myorders")
	public List<Order> ordersByUserId(@AuthenticationPrincipal Jwt jwt){
		
		return orderService.getOrdersByUserId(jwt.getSubject());
	}
	
	@GetMapping("/order/{orderId}")
	public Order getOrderById(@PathVariable String orderId) {
		return orderService.getOrderById(orderId);
	}
	
	
	@PostMapping("/checkout")
	public OrderItem createOrder(@RequestBody OrderItem orderItem,@AuthenticationPrincipal Jwt jwt) {
		Order order=new Order();
		order.setUserId(jwt.getSubject());
		order.setOrderedOn(new Date());
		order.setCost(orderItem.getCost());
		order.setStatus("PENDING");
	
		System.out.println(orderItem);
		Order ord=orderService.createOrder(order);
		OrderItem ordItem=null;
		if(ord!=null) {
			 ordItem=orderService.saveOrderItem(ord,orderItem);
			
		}
		return ordItem;
	}
	
	
	
	@PutMapping("/checkout/{orderId}/{addressId}")
	public Order addAddressToOrder(@PathVariable String orderId,@PathVariable String addressId) {
		return orderService.addAddressToOrder(orderId,addressId);
	}
	
	@GetMapping("/orders")
	public List<OrderItem> getOrders(@AuthenticationPrincipal Jwt jwt){
		return orderService.findOrders(jwt.getSubject());
		
	}

	
	@PutMapping("/place_order/{orderId}/{paymentId}")
	public Order addPaymentIdOrder(@PathVariable String orderId,@PathVariable String paymentId) {
		return orderService.addPaymentId(orderId,paymentId);
	}
}
