package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.dto.UserDTO;

public interface UserService {

	Optional<UserDTO> login(LoginDTO loginDTO);
	Optional<UserDTO> saveUser(RegisterDTO registerDTO);
	
}
