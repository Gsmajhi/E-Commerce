package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsmajhi.dao.Address;
import com.gsmajhi.service.UService;

@Controller
public class AddressController {

	@Autowired
	UService uService;
	@GetMapping("/addressform")
	public String addressForm(Model m) {
		
		m.addAttribute("address", new Address());
		return "addressform";
	}
	
	@GetMapping("/address/{orderId}")
	public String savedAddress(@PathVariable String orderId,Model m) {
		List<Address> address=uService.findAddress();
		if(address==null) {
			return "redirect:/addressform";
		}
		m.addAttribute("address",address);
		return "address";
		
	}
	
	
	
	
	@PostMapping("/addAddress")
	public String addAddress(@ModelAttribute Address address,RedirectAttributes attributes) {
		Address addr=uService.saveAddress(address);
		if(addr!=null) {
			return "redirect:/address";
		}
		else {
			attributes.addFlashAttribute("msg","something went wrong");
			return "redirect:/addressform";
		}
	}
	
	
	
}
