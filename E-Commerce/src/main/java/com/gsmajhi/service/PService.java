package com.gsmajhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gsmajhi.dao.Product;
import com.gsmajhi.feign.ProductServiceInterface;

@Service
public class PService {

	@Autowired
	ProductServiceInterface prod;
	
	
	public List<Product> getProducts(){
		return prod.getProducts();
	}
	
	public Product getProductById(String id) {
		return prod.getProductById(id);
	}
	
	
	public void deleteProduct(String id) {
		prod.deleteProduct(id);
	}
	
	public Product addProduct(Product product) {
		return prod.addProduct(product);
	}

public List<Product> findProductBySellerId(){
		return prod.getProductBySellerId();
	}
	
	public boolean addProductImage(MultipartFile image,String productId) {
		return prod.addImage(image, productId);
	}
}
