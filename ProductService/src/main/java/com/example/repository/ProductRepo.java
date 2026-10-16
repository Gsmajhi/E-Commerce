package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
	
	public Product getProductByProductId( String productId);
	
	List<Product> findBySellerId(String sellerId);
	
	List<Product> findByCategoryIgnoreCase(String category);
	
	List<Product> findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name,String descKeyword);

}
