package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsmajhi.entity.Cart;
import com.gsmajhi.entity.CartItems;
import com.gsmajhi.repository.CartRepo;
import com.gsmajhi.service.HelperService;



@RestController
public class CartController {
	
	@Autowired
	HelperService helperService;
	
	
	@Autowired
	CartRepo cartRepo;
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("productId") String productId, @AuthenticationPrincipal Jwt jwt) {
	
		String userId=jwt.getSubject();
		return helperService.addToCart(productId,userId);
	}
	
	@PostMapping("/deleteCart/{id}")
	public String deleteCart(@PathVariable String id) {
		 helperService.deleteCart(id);
		 return "cart deleted";
	}
	
	
	@PutMapping("/updateCart/{cartId}/{productId}")
	public String updateCart(@PathVariable String cartId,@PathVariable String productId,@RequestParam("quantity") int quantity ){
		return helperService.updateCart(cartId,productId, quantity);
		
	}
	
	@PostMapping("/removefromcart/{productId}")
	public void removeProductFromCart(@PathVariable String productId,@AuthenticationPrincipal Jwt jwt) {
		String userId=jwt.getSubject();
		Cart cart=cartRepo.getCartByUserId(userId);
		helperService.removeProductFromCart(cart.getCartId(), productId);
		
	}

	
	@GetMapping("/cart")
	public List<CartItems> getCartItems(@AuthenticationPrincipal Jwt jwt) {
		
		Cart cart=cartRepo.getCartByUserId(jwt.getSubject());
		if(cart==null) return null;
		return helperService.getCartItems(cart.getCartId());
	}
}
