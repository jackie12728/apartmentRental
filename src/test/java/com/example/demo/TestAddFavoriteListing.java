package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UserService;

@SpringBootTest
public class TestAddFavoriteListing {
	@Autowired
	private UserService userService;
	
	@Test
	public void test() {
		Long userId = 1L;
		Long listingId = 1L;
		userService.addFavoriteListing(userId, listingId);
	}
}
