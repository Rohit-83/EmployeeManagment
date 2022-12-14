package com.practice.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)  
//whenever some employee will not match then api will throw not found status from here
public class ResourceNotFoundException extends RuntimeException{

	
	private static final long serialVersionId = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
