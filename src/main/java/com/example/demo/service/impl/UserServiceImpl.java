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
import com.example.demo.util.Hash;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Optional<UserDTO> login(LoginDTO loginDTO) {
		
		Optional<User> optUser = userRepository.findByEmail(loginDTO.getEmail());
		if (optUser.isPresent()) {
			User user = optUser.get();
			
			// 比對密碼
			String passwordHash = Hash.getHash(loginDTO.getPassword(), user.getSalt());
			if(passwordHash.equals(user.getPasswordHash())) {
				return Optional.of(modelMapper.map(user, UserDTO.class));
			}
		}
		return Optional.empty();
	}

}
