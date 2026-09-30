package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsmajhi.dao.Product;
import com.gsmajhi.service.CartServiceHelper;

@Controller
public class CartController {
	

	@Autowired
	CartServiceHelper cartService;
	@GetMapping("/addToCart/{productId}")
	public String addToCart(@PathVariable("productId") String productId,RedirectAttributes redirectAttrs) {
		
		String userId="USR-10DD6234";
		String msg= cartService.addToCart(productId);
		redirectAttrs.addFlashAttribute("msg",msg);
		return "redirect:/home";
	}
	
	@GetMapping("/gotocart")
	public String goToCart(Model model) {
		List<Product> products=cartService.getCartItems();
		
		System.out.println(products);
		model.addAttribute("products", products);
		
			
		
		return "cart";
	}
	
	@PostMapping("/removefromcart/{productId}")
	public String removeProductFromCart(@PathVariable String productId) {
		cartService.removeFromCart(productId);
		return "redirect:/gotocart";
	}
}
