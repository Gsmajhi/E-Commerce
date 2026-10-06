package com.gsmajhi.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Address {
	
	@Id
	
	private String addressId;
	private String userId;
	private String name;
	private String mobileNumber;
	private String Area;
	private String Street;
	private String pin;
	private String district;
	private String state;
	private String landmark;
	
	
	@PrePersist
	public void generateId() {
		if(addressId==null) {
			addressId="ADRS-"+UUID.randomUUID().toString()
					.substring(0, 8);
		}
	}
	

}
