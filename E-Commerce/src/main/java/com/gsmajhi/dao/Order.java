package com.gsmajhi.dao;

import lombok.Data;

@Data
public class Order {

	private String orderId;
	private String userId;
	private String productId;
	private int quantity;
	private int cost;
}
