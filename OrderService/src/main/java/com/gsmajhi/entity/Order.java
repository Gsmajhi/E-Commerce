package com.gsmajhi.entity;

import java.util.Date;

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
	private int cost;
	private Date orderedOn;
	private String addressId;
	private String paymentId;
	private String status;
}
