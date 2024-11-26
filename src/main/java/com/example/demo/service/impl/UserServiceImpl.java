package com.example.demo.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Optional<UserDTO> findByUsername(String username) {
		Optional<User> optUser = userRepository.findByUsername(username);
		if(optUser.isEmpty()) return Optional.empty();
		// 利用 modelMapper 將 User 轉 UserDTO
		UserDTO userDTO = modelMapper.map(optUser.get(), UserDTO.class);
		return Optional.of(userDTO);
	}

	@Override
	public Optional<UserDTO> login(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<UserDTO> saveUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
