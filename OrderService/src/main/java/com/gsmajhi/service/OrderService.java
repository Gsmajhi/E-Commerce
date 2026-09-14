package com.gsmajhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsmajhi.entity.Order;
import com.gsmajhi.repository.OrderRepo;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}
	
	
	public List<Order> allOrders(){
		return orderRepo.findAll();
	}
	
	public List<Order> getOrdersByUserId(String id){
		return orderRepo.getOrdersByUserId(id);
	}


	public Order getOrderById(String orderId) {
		
		return orderRepo.getById(orderId);
	}
}
