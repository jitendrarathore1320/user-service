package com.advantal.exceptionHandling;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BadRequestException(String s) {
		super(s);
	}
	
}
