package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.Permission;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserStatus;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserStatusRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Hash;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private UserStatusRepository userStatusRepository;
	
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

	@Override
	public Optional<UserDTO> saveUser(RegisterDTO registerDTO) {
		String salt = Hash.getSalt(); // 得到隨機鹽
		String passwordHash = Hash.getHash(registerDTO.getPassword(), salt);
		Optional<Permission> permission = permissionRepository.findById(1L);
		Optional<UserStatus> userStatus = userStatusRepository.findById(1L);
		
		// 將上列參數封裝到 User 物件中
		User user = modelMapper.map(registerDTO, User.class);
		user.setSalt(salt);
		user.setPasswordHash(passwordHash);
		user.setPermission(permission.get());
		user.setCreatedAt(LocalDateTime.now());
		user.setUserStatus(userStatus.get());
		user = userRepository.save(user);
		
		return Optional.of(modelMapper.map(user, UserDTO.class));
	}

}
