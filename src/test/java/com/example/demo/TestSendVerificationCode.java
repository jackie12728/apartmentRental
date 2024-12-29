package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UserService;

@SpringBootTest
public class TestSendVerificationCode {

	@Autowired
	private UserService userService;
	
	@Test
	public void test() {
		String recipientEmail = "as212638@gmail.com";
		userService.sendVerificationCode(recipientEmail);
	}
}
