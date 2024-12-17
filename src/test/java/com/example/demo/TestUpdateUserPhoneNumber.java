package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.SimpleUserDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;

@SpringBootTest
public class TestUpdateUserPhoneNumber {

	@Autowired
	private UserService userService;
	
	@Test
	public void test() {
		Long id = 1L;
		String phoneNumber = "0912392024";
		SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
		simpleUserDTO.setId(id);
		simpleUserDTO.setPhoneNumber(phoneNumber);
		Optional<UserDTO> uOptional = userService.updateUserPhoneNumber(simpleUserDTO);
		System.out.println(uOptional);
	}
}
