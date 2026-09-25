package com.gsmajhi.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Cart {
	@Id
	private String cartId;
	private String userId;
	
	@PrePersist
	public void generateId() {
		if(cartId==null) {
			cartId="CART-"+UUID.randomUUID().toString().substring(0,8);
		}
	}

}
