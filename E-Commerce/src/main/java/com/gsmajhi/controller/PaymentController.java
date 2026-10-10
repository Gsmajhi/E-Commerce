package com.gsmajhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsmajhi.dao.Order;
import com.gsmajhi.service.OService;

@Controller
public class PaymentController {

	@Autowired
	OService oService;
	@PostMapping("/payment/{orderId}")
	public String addPaymentIdToOrder(@PathVariable String orderId,@RequestParam("paymentmethod") String paymentId,Model model) {
		if(paymentId!="CASH-123") {
			Order order=oService.addPaymentIdToOrder(orderId,paymentId);
			if(order==null) {
				model.addAttribute("msg","Something went wrong");
			}
			else {
				model.addAttribute("msg","order Confirmed");
			}
		}
		
		return "ordersuccess";
	}
}
