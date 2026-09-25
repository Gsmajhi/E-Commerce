package com.gsmajhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsmajhi.dao.Order;
import com.gsmajhi.feign.OrderServiceInterface;

@Service
public class OService {
	
	@Autowired
	OrderServiceInterface osInterface;
	
	public String createOrder(Order order) {
		return osInterface.createOrder(order);
	}
	
	public List<Order> orders() {
		return osInterface.ordersByUserId();
	}
}
