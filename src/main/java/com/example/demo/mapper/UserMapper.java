package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;

@Component // 此元件由 Springboot 自動掃描後管理
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO toDto(User user) {
		// Entity 轉 DTO
		return modelMapper.map(user, UserDTO.class);
	}
	
	public User toEntity(UserDTO userDto) {
		// DTO 轉 Entity
		return modelMapper.map(userDto, User.class);
	}
}
