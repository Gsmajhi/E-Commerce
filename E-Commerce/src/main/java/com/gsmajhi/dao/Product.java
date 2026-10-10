package com.gsmajhi.dao;

import lombok.Data;

@Data
public class Product {
	private String productId;
	private String productName;
	private String price;
	private String description;
	private String  category;
	private int quantity;
	private String imagePath;
}
