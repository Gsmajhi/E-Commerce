package com.gsmajhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsmajhi.dto.Product;
import com.gsmajhi.entity.Order;
import com.gsmajhi.entity.OrderItem;
import com.gsmajhi.feign.ProductServiceInterface;
import com.gsmajhi.repository.OrderItemRepo;
import com.gsmajhi.repository.OrderRepo;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	ProductServiceInterface productServiceInterface;
	
	@Autowired
	OrderItemRepo orderItemRepo;
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
	
	public OrderItem saveOrderItem(Order order,OrderItem ordItem) {
		
		Product product=productServiceInterface.getProductById(ordItem.getProductId());
		
		
		
		ordItem.setOrderId(order.getOrderId());
		
		ordItem.setSellerId(product.getSellerId());
		
		
		return orderItemRepo.save(ordItem);
	}
	
	public List<OrderItem> findOrders(String sellerId){
		return orderItemRepo.findOrderItemBySellerId(sellerId);
	}
}
