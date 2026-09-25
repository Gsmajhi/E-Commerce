package com.gsmajhi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsmajhi.entity.Cart;
import com.gsmajhi.entity.CartItems;
import com.gsmajhi.repository.CartItemsRepo;
import com.gsmajhi.repository.CartRepo;

@Service
public class HelperService {

	@Autowired
	CartRepo cartRepo;

	@Autowired
	CartItemsRepo cartItemsRepo;
	
	
	public String addToCart(String productId,String userId) {
		Cart cart = cartRepo.getCartByUserId(userId);
		
		if (cart == null) {

			Cart c = new Cart();
			c.setUserId(userId);

			 cart=cartRepo.save(c);
			
		} 
		CartItems cartItems=cartItemsRepo.findByCartIdAndProductId(cart.getCartId(),productId);

		if(cartItems==null) {
			cartItems=new CartItems();
			cartItems.setCartId(cart.getCartId());
			cartItems.setProductId(productId);
			cartItems.setQuantity(1);
		}else {
			cartItems.setQuantity(cartItems.getQuantity()+1);
		}

		CartItems savedCartItems = cartItemsRepo.save(cartItems);
		if (savedCartItems != null) {
			return "Added to Cart";
		} else {
			return "Something went wrong";
		}
	}

	public void deleteCart(String id) {
		cartRepo.deleteById(id);
		cartItemsRepo.deleteByCartId(id);
	}

	public String updateCart(String cartId,String productId,int quantity) {
		CartItems cartItems=cartItemsRepo.findByCartIdAndProductId(cartId,productId);
		cartItems.setQuantity(quantity);
		
		CartItems savedCartItems=cartItemsRepo.save(cartItems);
		if(savedCartItems!=null) return "Cart Updated";
		else return "Someting went wrong";
	}

	public void removeProductFromCart(String cartId,String productId) {
		CartItems crt=cartItemsRepo.findByCartIdAndProductId(cartId,productId);
		cartItemsRepo.delete(crt);
		
	}

	public List<CartItems> getCartItems(String cartId) {
		return cartItemsRepo.findByCartId(cartId);
		
		
	}

}
