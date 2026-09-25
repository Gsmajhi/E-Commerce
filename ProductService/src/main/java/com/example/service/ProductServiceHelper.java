package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.repository.ProductRepo;

@Service
public class ProductServiceHelper {

	@Autowired
	ProductRepo productRepo;
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	
	public List<Product> getProductBySellerId(String sellerId){
		return productRepo.findBySellerId(sellerId);
	}
}
