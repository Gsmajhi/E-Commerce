package com.gsmajhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsmajhi.dao.Product;
import com.gsmajhi.service.OService;
import com.gsmajhi.service.PService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	PService ps;
	
	@Autowired
	OService oService;
	
	
	@GetMapping("/home")
	public String getProducts(HttpSession session,Model m){
		
		
		
		List<Product> list=ps.getProducts();
		List<Product> product=ps.addImagePathToProduct(list);
		
		m.addAttribute("list", product);
		
		return "index";
	}
	
	
	@GetMapping("/showproduct/{id}")
	public String showProduct(@PathVariable String id,Model m) {
		Product p=ps.getProductById(id);
		m.addAttribute("product",p);
		return "showproduct";
	}
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable String id) {
		return ps.getProductById(id);
	}
	
	@GetMapping("/showDeletepage")
	public String showDeletepage() {
		return "deleteproduct";
	}
	
	@PostMapping("/deleteProduct")
	
	public String deleteProduct(@RequestParam("productId") String id) {
		ps.deleteProduct(id);
		return "redirect:/showDeletepage";
	}
	
	
	
	
}
