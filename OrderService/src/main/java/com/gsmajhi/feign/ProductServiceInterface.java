package com.gsmajhi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gsmajhi.dto.Product;

@FeignClient("ProductService")
public interface ProductServiceInterface {
	@GetMapping("/products/{productId}")
	public Product getProductById(@PathVariable String productId);
}
