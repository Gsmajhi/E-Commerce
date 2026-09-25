package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsmajhi.dao.Order;
import com.gsmajhi.service.OService;

import io.jsonwebtoken.Jwt;

@Controller
public class OrderController {
	
	@Autowired
	OService oService;
	@PostMapping("/createOrder")
	public String createOrder(@RequestParam("productId") String id,@RequestParam("price") String price,
			@RequestParam("quantity") String quantity,Model model) {
		Order order=new Order();
		
		
		int pPrice=Integer.parseInt(price);
		
		
		int quant=Integer.parseInt(quantity);
		order.setProductId(id);
		order.setQuantity(quant);
		order.setCost(quant*pPrice);
		
		String msg= oService.createOrder(order);
		model.addAttribute("msg",msg);
		return "ordersuccess";
	}
	
	@GetMapping("/orders")
	public String getAllOrders(Model model){
		List<Order> orders=oService.orders();
		model.addAttribute("orders", orders);
		return "allorders";
	}
}
