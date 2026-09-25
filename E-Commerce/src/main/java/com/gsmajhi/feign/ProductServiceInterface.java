package com.gsmajhi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsmajhi.dao.Product;

@FeignClient("ProductService")
public interface ProductServiceInterface {

	@GetMapping("/allproducts")
	public List<Product> getProducts();
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id);
	@GetMapping("/products/{productId}")
	public Product getProductById(@PathVariable String productId);
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product product);
	
	@GetMapping("/productbyseller")
	public List<Product> getProductBySellerId();
}
