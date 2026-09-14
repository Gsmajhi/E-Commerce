package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="E-commerceProduct")
public class Product {

	@Id
	@GeneratedValue(generator="Custom-id")
	@GenericGenerator(name="Custom-id",strategy="com.example.idGenerator.CustomIdGenerator")
	private String productId;
	private String productName;
	private String price;
	private String description;
	private String category;
	
}
