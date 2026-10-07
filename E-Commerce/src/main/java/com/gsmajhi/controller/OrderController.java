package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsmajhi.dao.Order;
import com.gsmajhi.dao.OrderItem;
import com.gsmajhi.service.OService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	@Autowired
	OService oService;
	@PostMapping("/checkout")
	public String createOrder(@RequestParam("productId") String id,@RequestParam("price") String price,
			@RequestParam("quantity") String quantity,Model model, HttpSession session) {
		String token=(String) session.getAttribute("token");
		if(token==null) {
			return "redirect:/loginpage";
		}
		OrderItem orderItem=new OrderItem();
		
		
		int pPrice=Integer.parseInt(price);
		
		
		int quant=Integer.parseInt(quantity);
		orderItem.setProductId(id);
		orderItem.setQuantity(quant);
		orderItem.setCost(quant*pPrice);
		orderItem.setPrice(pPrice);
		


		OrderItem ordItem= oService.createOrder(orderItem);
		if(ordItem!=null) {
			String orderId=ordItem.getOrderId();
			return "redirect:/address/"+ orderId;
		}
		else {
			model.addAttribute("msg","something went wrong");
			return "ordersuccess";
		}
	}
	
	
	@PostMapping("/checkout/{orderId}")
	public String addAddressToOrder(@PathVariable String orderId,@RequestParam("selectedAddress") String addressId,Model m) {
		Order order=oService.addAddressToOrder(orderId, addressId);
		if(order!=null) {
			return "paymentpage";
		}else {
			m.addAttribute("msg","something went worng");
			return "ordersuccess";
		}
		
	}
	
	
	
	@GetMapping("/myorders")
	public String getAllOrders(Model model){
		List<Order> orders=oService.orders();
		model.addAttribute("orders", orders);
		return "allorders";
	}
	
	@GetMapping("/orders")
	public String getOrders(Model model) {
		List<OrderItem> orderItem=oService.findOrders();
		model.addAttribute("orderItems",orderItem);
		return "orders";
	}
}
