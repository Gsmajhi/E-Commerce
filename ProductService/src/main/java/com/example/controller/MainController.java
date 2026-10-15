package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.repository.CategoryRepo;
import com.example.repository.ProductRepo;
import com.example.service.ProductServiceHelper;

@RestController
public class MainController {
	
	@Autowired
	ProductRepo productrepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	@Value("${file.upload-dir}")
    private String uploadDir;
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
	
//	
//	@PostMapping(
//		    value = "/addProduct",
//		    consumes = MediaType.APPLICATION_JSON_VALUE
//		)
	
	@PostMapping("/addProduct")
	
	public Product addProduct(@RequestBody Product product,@AuthenticationPrincipal Jwt jwt) throws IOException {
		
		
		//ObjectMapper mapper=new ObjectMapper();
		//Product product=mapper.readValue(productjson, Product.class);
		
		
		product.setSellerId(jwt.getSubject());
		
		return productServiceHelper.addProduct(product);
		
	}
	
	@PostMapping(
		    value = "/addImage/{productId}",
		    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
		)
	public boolean addImage(@RequestPart("image") MultipartFile image,@PathVariable String productId) throws IOException {
		String imagePath=productServiceHelper.saveImage(image);
		Optional<Product> optional=productrepo.findById(productId);
		
		Product product=optional.get();
		product.setImagePath(imagePath);
		productrepo.save(product);
		if(imagePath!=null) return true;
		else {
			return false;
		}
	}
	
	
	@PutMapping("/updateproduct/{id}")
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
	
	@GetMapping("/images/{fileName}")
	public ResponseEntity<Resource> getImage(
	        @PathVariable String fileName) throws IOException {

		System.out.println("reached");
	    Path filePath = Paths.get(uploadDir)
	            .resolve(fileName)
	            .normalize();

	    Resource resource = new UrlResource(filePath.toUri());

	    if (!resource.exists()) {
	        return ResponseEntity.notFound().build();
	    }

	    String contentType = Files.probeContentType(filePath);

	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType(contentType))
	            .body(resource);
	}
	
	
	 @GetMapping("/test-role")
	    public String testRole() {

	        Authentication auth =
	                SecurityContextHolder.getContext()
	                                     .getAuthentication();

	        if (auth == null) {
	            return "Authentication is null";
	        }

	        System.out.println("Principal: " + auth.getPrincipal());
	        System.out.println("Authorities: " + auth.getAuthorities());
	        System.out.println("Authenticated: " + auth.isAuthenticated());

	        return "Authorities: " + auth.getAuthorities();
	    }
	 
	 @GetMapping("/categories")
	 public List<Category> getCategory(){
		return productServiceHelper.categories();
		 
	 }
	 
	 
	 @GetMapping("/category/{categoryName}")
	 public List<Product> getProductByCategory(@PathVariable String categoryName){
		return productServiceHelper.getProductByCategory(categoryName);
	 }
}
