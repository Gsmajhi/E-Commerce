package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsmajhi.entity.Order;
import com.gsmajhi.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	
	@GetMapping("/orders")
	public List<Order> allOrders(){
		return orderService.allOrders();
	}
	
	
	@GetMapping("/orders/{userId}")
	public List<Order> ordersByUserId(@PathVariable String userId){
		return orderService.getOrdersByUserId(userId);
	}
	
	@GetMapping("/order/{orderId}")
	public Order getOrderById(@PathVariable String orderId) {
		return orderService.getOrderById(orderId);
	}
	
	
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody Order order) {
		Order ord=orderService.createOrder(order);
		
		if(ord!=null) {
			return "order added";
		}else {
			return "something went wrong";
		}
	}

}
