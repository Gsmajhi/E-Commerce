package com.gsmajhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsmajhi.entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Integer>{

	List<OrderItem> findOrderItemBySellerId(String sellerId);
}
