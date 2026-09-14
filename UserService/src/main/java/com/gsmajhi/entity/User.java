package com.gsmajhi.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="EcomUsers")
@Data
public class User {
	
	@Id
	@GeneratedValue(generator="Custom-id")
	@GenericGenerator(name="Custom-id",strategy="com.gsmajhi.idGenerator.CustomIdGenerator")
	private String id;
	private String name;
	private String email;
	private String password;
	private String role;

}
