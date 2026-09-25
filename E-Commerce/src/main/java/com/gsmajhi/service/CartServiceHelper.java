package com.gsmajhi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsmajhi.dao.CartItems;
import com.gsmajhi.dao.Product;
import com.gsmajhi.feign.CartServiceInterface;
import com.gsmajhi.feign.ProductServiceInterface;

@Service
public class CartServiceHelper {

	@Autowired
	CartServiceInterface cartServiceInterface; 
	
	@Autowired
	ProductServiceInterface productServiceInterface;
	
	public String addToCart(String productId) {
		return cartServiceInterface.addToCart(productId);
	}

	public List<Product> getCartItems() {
		
		List<Product> ans=new ArrayList<>();
		
		List<CartItems> cartItems =cartServiceInterface.getCartItems();
		if(cartItems==null) return null;
		for(CartItems items:cartItems) {
			Product product=productServiceInterface.getProductById(items.getProductId());
			
			Product prodto=new Product();
			prodto.setProductName(product.getProductName());
			prodto.setDescription(product.getDescription());
			prodto.setPrice(product.getPrice());
			prodto.setProductId(product.getProductId());
			prodto.setQuantity(items.getQuantity());
			ans.add(prodto);
		}
		
		return ans;
	}
	
	public void removeFromCart(String productId) {
		cartServiceInterface.removeProductFromCart(productId);
	}
}
