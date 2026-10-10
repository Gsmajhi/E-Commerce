package com.gsmajhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsmajhi.dao.Order;
import com.gsmajhi.dao.OrderItem;
import com.gsmajhi.feign.OrderServiceInterface;

@Service
public class OService {
	
	@Autowired
	OrderServiceInterface osInterface;
	
	public OrderItem createOrder(OrderItem orderItem) {
		return osInterface.createOrder(orderItem);
	}
	
	public List<Order> orders() {
		return osInterface.ordersByUserId();
	}
	
	public List<OrderItem> findOrders(){
		return osInterface.getOrders();
	}
	
	
	public Order addAddressToOrder(String orderId,String addressId) {
		return osInterface.addAddressToOrder(orderId, addressId);
	}
	
	public Order addPaymentIdToOrder(String orderId,String paymentId) {
		return osInterface.addPaymentIdToOrder(orderId,paymentId);
	}
}
