package com.gsmajhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsmajhi.entity.CartItems;
@Repository
public interface CartItemsRepo extends JpaRepository<CartItems,Integer>{

	CartItems findByCartIdAndProductId(String cartId, String productId);
	void deleteByCartId(String cartId);
	List<CartItems> findByCartId(String cartId);
	void deleteCartItemsByProductId(String productId);
}
