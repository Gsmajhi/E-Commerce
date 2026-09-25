package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gsmajhi.dao.Product;
import com.gsmajhi.service.PService;

@Controller
public class ProductController {
	
	@Autowired
	PService pService;
	
	@GetMapping("/productform")
	public String showProductForm(Model m) {
		
		
		m.addAttribute("product",new Product());
		
		return "productform";
	}
	
	
	@PostMapping("/addproduct")
	public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAtt) {
		
		String msg =pService.addProduct(product);
		
		redirectAtt.addFlashAttribute("msg",msg);
		return "redirect:/productform";
		
	}

	@GetMapping("/myproducts")
	public String getProducts(Model m){
		List<Product> product=pService.findProductBySellerId();
		
		m.addAttribute("product",product);
		return "myproducts";
	}
}
