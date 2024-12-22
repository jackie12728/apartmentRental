package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ListingNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.dto.FavoriteListingDTO;
import com.example.demo.model.dto.FavoriteUserDTO;
import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.dto.SimpleUserDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.Listing;
import com.example.demo.model.entity.Permission;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserStatus;
import com.example.demo.repository.ListingRepository;
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
	private ListingRepository listingRepository;
	
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

	@Override
	public Optional<UserDTO> updateUserPhoneNumber(SimpleUserDTO simpleUserDTO) {
		
		// 1. 根據 ID 查找目標 User
	    return userRepository.findById(simpleUserDTO.getId()).map(existingUser -> {
	        // 2. 更新 phoneNumber
	        existingUser.setPhoneNumber(simpleUserDTO.getPhoneNumber());
	        
	        // 3. 保存更新後的 User
	        User updatedUser = userRepository.save(existingUser);
	        
	        // 4. 映射為 UserDTO 返回
	        return modelMapper.map(updatedUser, UserDTO.class);
	    });
	}

	// 用戶關注列表(用戶關注那些房屋)
	@Override
	public List<FavoriteListingDTO> getFavoriteListings(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		// 該用戶關注的房屋集合
		Set<Listing> listings = user.getFavoriteListings();
		// 將 listings 集合中每一個 Listing 元素轉 FavoriteListingDTO
		return listings.stream()
					   .map(listing -> modelMapper.map(listing, FavoriteListingDTO.class)) // 元素轉換
					   .toList();
	}

	// 房屋關注列表(房屋被那些用戶關注)
	@Override
	public List<FavoriteUserDTO> getFavoriteUsers(Long listingId) {
		Listing listing = listingRepository.findById(listingId).orElseThrow(() -> new ListingNotFoundException());
		// 該房屋被那些用戶所關注的集合
		Set<User> users = listing.getFavoriteUsers();
		// 將 users 集合中每一個 User 元素轉 FavoriteUserDTO
		return users.stream()
					.map(user -> modelMapper.map(user, FavoriteUserDTO.class))
					.toList();
	}

	// 新增房屋關注
	@Override
	public void addFavoriteListing(Long userId, Long listingId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		Listing listing = listingRepository.findById(listingId).orElseThrow(() -> new ListingNotFoundException());
		// 將房屋加入到用戶關注清單
		user.getFavoriteListings().add(listing);
		// 保存關係
		userRepository.save(user);
	}

	// 移除房屋關注
	@Override
	public void removeFavoriteListing(Long userId, Long listingId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		Listing listing = listingRepository.findById(listingId).orElseThrow(() -> new ListingNotFoundException());
		// 將房屋從用戶關注清單中移除
		user.getFavoriteListings().remove(listing);
		// 保存關係
		userRepository.save(user);
	}

}
