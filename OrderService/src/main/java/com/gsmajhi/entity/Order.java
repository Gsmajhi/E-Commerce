package com.gsmajhi.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(generator="Custom-id")
	@GenericGenerator(name="Custom-id",strategy="com.gsmajhi.idGenerator.CustomIdGenerator")
	private String orderId;
	private String userId;
	private String productId;
	private int quantity;
	private int cost;

}
