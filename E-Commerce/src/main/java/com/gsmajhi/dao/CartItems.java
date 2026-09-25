package com.gsmajhi.dao;

import lombok.Data;

@Data

public class CartItems {
	
	
	private int id;
	private String cartId;
	private String productId;
	private int quantity;

}