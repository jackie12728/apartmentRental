package com.example.demo.exception;

// 房屋沒找到的例外
public class ListingNotFoundException extends RuntimeException {
	public ListingNotFoundException() {
		super();
	}
	
	public ListingNotFoundException(String message) {
		super(message);
	}
}
