package com.plexus.superheroes.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SuperHeroException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public SuperHeroException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
