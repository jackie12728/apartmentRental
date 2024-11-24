package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

@Transactional
@SpringBootTest
class SpringbootMvcApartmentRentalApplicationTests {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void testUserMapper() {
		User user = userRepository.findByEmail("jack@gmail.com");
		System.out.println("原始 user: " + user);
		
		// toDto 將 Entity 轉 DTO
		UserDto userDto = userMapper.toDto(user);
		System.out.println("測試 toDto: " + userDto);
		
		// toEntity 將 DTO 轉 Entity
		user = userMapper.toEntity(userDto);
		System.out.println("測試 toEntity: " + user);
	}

}
