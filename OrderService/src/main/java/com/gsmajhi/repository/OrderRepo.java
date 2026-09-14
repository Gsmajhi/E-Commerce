package com.gsmajhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsmajhi.entity.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order,String> {
	public List<Order> getOrdersByUserId(String id);
}	
