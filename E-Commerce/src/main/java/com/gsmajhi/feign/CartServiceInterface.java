package com.gsmajhi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsmajhi.dao.CartItems;

@FeignClient("CartService")
public interface CartServiceInterface {
	
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("productId") String productId); 
	
	@PostMapping("/deleteCart/{id}")
	public String deleteCart(@PathVariable String id);
	
	
	@PutMapping("/updateCart/{cartId}/{productId}")
	public String updateCart(@PathVariable String cartId,@PathVariable String productId,@RequestParam("quantity") int quantity );
	
	@PostMapping("/removefromcart/{productId}")
	public void removeProductFromCart(@PathVariable String productId);
	@GetMapping("/cart")
	public List<CartItems> getCartItems();
}
