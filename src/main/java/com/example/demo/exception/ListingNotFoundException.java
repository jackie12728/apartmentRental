package com.example.demo.exception;

public class ListingNotFoundException extends RuntimeException {
	public ListingNotFoundException() {
		super();
	}
	
	public ListingNotFoundException(String message) {
		super(message);
	}
}
