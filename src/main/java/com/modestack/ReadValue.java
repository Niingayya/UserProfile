package com.modestack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadValue {
	@Value("${salt.name}")
	private String saltName;

	public String getSaltName() {
		return saltName;
	}

	public void setSaltName(String saltName) {
		this.saltName = saltName;
	}
	
	

}
