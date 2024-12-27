package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UserService;

@SpringBootTest
public class TestRedis {

	@Autowired
	private UserService userService;
	
	@Test
	public void test() {
		userService.testRedis();
	}
}
