package com.gsmajhi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.gsmajhi.dao.Category;
import com.gsmajhi.dao.Product;

@FeignClient(name="ProductService" )
public interface ProductServiceInterface {

	@GetMapping("/allproducts")
	public List<Product> getProducts();
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id);
	@GetMapping("/products/{productId}")
	public Product getProductById(@PathVariable String productId);
	
	
	
	@PutMapping("/updateproduct/{id}")
	public String updateProduct(@PathVariable String id,@RequestBody Product product);
	
	
//	@PostMapping(
//		    value = "/addProduct",
//		    consumes = MediaType.APPLICATION_JSON_VALUE
//		)
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) ;
	
	@GetMapping("/productbyseller")
	public List<Product> getProductBySellerId();
	
	
	@PostMapping(
		    value = "/addImage/{productId}",
		    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
		)
	
	public boolean addImage(@RequestPart("image") MultipartFile image,@PathVariable("productId") String productId);
	
	 @GetMapping("/categories")
	 public List<Category> getCategory();
	 
	 @GetMapping("/category/{categoryName}")
	 public List<Product> getProductByCategory(@PathVariable String categoryName);
	
}
