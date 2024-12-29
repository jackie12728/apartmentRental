package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UserService;

@SpringBootTest
public class TestValidateCode {

	@Autowired
	private UserService userService;
	
	@Test
	public void test() {
		String email = "as212638@gmail.com";
		String code = "7574";
		boolean test = userService.validateCode(email, code);
		if (test) {
		    System.out.println("驗證成功");
		} else {
		    System.out.println("驗證失敗");
		}

	}
}
