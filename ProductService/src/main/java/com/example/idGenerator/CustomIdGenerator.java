package com.example.idGenerator;

import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator{

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		
		return "PRO-"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
	}

}
