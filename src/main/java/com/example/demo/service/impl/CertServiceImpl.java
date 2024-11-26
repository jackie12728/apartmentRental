package com.example.demo.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CertException;
import com.example.demo.exception.PasswordInvalidException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertService;
import com.example.demo.util.Hash;

@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserCert getCert(String email, String password) throws CertException {
		// 1.是否有此人
//		User user = userRepository.findByEmail(email);
//		if (user == null) {
//			throw new UserNotFoundException();
//		}

		Optional<User> optUser = userRepository.findByEmail(email);
		if (optUser.isEmpty()) {
			throw new UserNotFoundException();
		}
		// 利用 modelMapper 將 optUser 轉 User
		User user = modelMapper.map(optUser.get(), User.class);

		// 2.比對密碼
		String passwordHash = Hash.getHash(password, user.getSalt());
		if (!passwordHash.equals(user.getPasswordHash())) {
			throw new PasswordInvalidException();
		}

		// 3. 簽發憑證
//		UserCert userCert = new UserCert(user.getId(), user.getUsername(), user.getPermission());
//		return userCert;
		return null;
	}

}
