package com.gsmajhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gsmajhi.dao.Category;
import com.gsmajhi.dao.Product;
import com.gsmajhi.feign.ProductServiceInterface;

@Service
public class PService {

	@Autowired
	ProductServiceInterface prod;
	
	
	public List<Product> getProducts(){
		return prod.getProducts();
	}
	
	public Product getProductById(String id) {
		return prod.getProductById(id);
	}
	
	
	public void deleteProduct(String id) {
		prod.deleteProduct(id);
	}
	
	public Product addProduct(Product product) {
		return prod.addProduct(product);
	}

public List<Product> findProductBySellerId(){
		return prod.getProductBySellerId();
	}
	
	public boolean addProductImage(MultipartFile image,String productId) {
		return prod.addImage(image, productId);
	}
	
	
	public String updateProduct(String productId,Product product) {
		return prod.updateProduct(productId,product);
	}
	
	public List<Category> getCategories(){
		return prod.getCategory();
	}

	public List<Product> getProductByCategory(String categoryName) {
		
		return prod.getProductByCategory(categoryName);
	}
	
	public List<Product> searchProduct(String keyword){
		return prod.searchProduct(keyword);
	}
	
	public List<Product> addImagePathToProduct(List<Product> product){
		return product.stream().map(prod -> {
		    Product pro = new Product();
		    
		    pro.setProductId(prod.getProductId());
		    pro.setProductName(prod.getProductName());
		    pro.setDescription(prod.getDescription());
		    pro.setCategory(prod.getCategory());
		    pro.setPrice(prod.getPrice());
		    pro.setQuantity(prod.getQuantity()); 
		    pro.setImagePath("http://localhost:8080/images/" + prod.getImagePath());
		    
		    return pro;
		}).toList();

	}
}
