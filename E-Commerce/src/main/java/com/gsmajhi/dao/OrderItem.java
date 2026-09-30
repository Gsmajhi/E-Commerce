package com.gsmajhi.dao;

import lombok.Data;

@Data
public class OrderItem {
	
	private int id;
	private String orderId;
	private String productId;
	private int quantity;
	private int price;
	private int cost;
	private String sellerId;
}
