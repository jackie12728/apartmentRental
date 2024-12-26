package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.dto.FavoriteListingDTO;
import com.example.demo.model.dto.FavoriteUserDTO;
import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.dto.SimpleUserDTO;
import com.example.demo.model.dto.UserDTO;

public interface UserService {

	Optional<UserDTO> login(LoginDTO loginDTO);

	String saveUser(RegisterDTO registerDTO);

	Optional<UserDTO> updateUserPhoneNumber(SimpleUserDTO simpleUserDTO);

	// 用戶關注列表(用戶關注那些商品)
	public List<FavoriteListingDTO> getFavoriteListings(Long userId);

	// 商品關注列表(商品被那些用戶關注)
	public List<FavoriteUserDTO> getFavoriteUsers(Long listingId);

	// 新增商品關注
	public void addFavoriteListing(Long userId, Long listingId);

	// 移除商品關注
	public void removeFavoriteListing(Long userId, Long listingId);

}
