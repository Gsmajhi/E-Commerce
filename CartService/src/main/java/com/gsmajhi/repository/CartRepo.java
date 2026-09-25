package com.gsmajhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsmajhi.entity.Cart;
@Repository
public interface CartRepo extends JpaRepository<Cart,String> {

	public Cart getCartByUserId(String userId);
}
