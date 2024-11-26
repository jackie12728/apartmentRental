package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.UserDTO;

public interface UserService {

	Optional<UserDTO> findByUsername(String username);
	Optional<UserDTO> login(LoginDTO loginDTO);
	Optional<UserDTO> saveUser(UserDTO userDTO);
	
}
