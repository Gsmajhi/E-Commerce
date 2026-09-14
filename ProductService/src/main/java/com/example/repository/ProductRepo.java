package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
	
	public Product getProductByProductId( String productId);

}
