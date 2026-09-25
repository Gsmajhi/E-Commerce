package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.repository.ProductRepo;
import com.example.service.ProductServiceHelper;

@RestController
public class MainController {
	
	@Autowired
	ProductRepo productrepo;
	
	@Autowired
	ProductServiceHelper productServiceHelper;
	@GetMapping("/allproducts")
	public List<Product> getProducts(){
		return productrepo.findAll();
	}
	
	@GetMapping("/productbyseller")
	public List<Product> getProductBySellerId(@AuthenticationPrincipal Jwt jwt){
		String sellerId=jwt.getSubject();
		return productServiceHelper.getProductBySellerId(sellerId);
	}
	
	@GetMapping("/products/{productId}")
	public Product getProductById(@PathVariable String productId) {
		return productrepo.getProductByProductId(productId);
	}
	
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product,@AuthenticationPrincipal Jwt jwt) {
		product.setSellerId(jwt.getSubject());
		Product pro=productServiceHelper.addProduct(product);
		if(pro!=null) return "Product Added";
		else return "something went wrong";
	}
	@PutMapping("/updateProduct/{id}")
	public String updateProduct(@PathVariable String id,@RequestBody Product product) {
		Optional<Product> p=productrepo.findById(id);
		Product pr=p.get();
		pr.setProductName(product.getProductName());
		pr.setDescription(product.getDescription());
		pr.setPrice(product.getPrice());
		Product pro=productrepo.save(pr);
		if(pro!=null) return "Product Updated";
		else return "somthing went wrong";
	}
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id) {
		productrepo.deleteById(id);
		return "Product deleted";
	}
}
