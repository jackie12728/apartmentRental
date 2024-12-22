package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aop.CheckUserSession;
import com.example.demo.model.dto.FavoriteListingDTO;
import com.example.demo.model.dto.FavoriteUserDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FavoriteController {
	
	@Autowired
	private UserService userService;
	
	// 獲取用戶關注清單
	@GetMapping
	@CheckUserSession
	public ResponseEntity<ApiResponse<List<FavoriteListingDTO>>> getFavoriteListings(HttpSession session) {
		Long userId = ((UserDTO)session.getAttribute("userDTO")).getId();
		List<FavoriteListingDTO> favorites = userService.getFavoriteListings(userId);
		return ResponseEntity.ok(ApiResponse.success("獲取關注成功清單", favorites));
	}
	
	// 獲取商品被關注清單
	@GetMapping("/listing/{listingId}")
	public ResponseEntity<ApiResponse<List<FavoriteUserDTO>>> getFavoriteUsers(@PathVariable Long listingId) {
		List<FavoriteUserDTO> users = userService.getFavoriteUsers(listingId);
		return ResponseEntity.ok(ApiResponse.success("獲取房屋被關注清單", users));
	}
	
	// 加入關注
	@PostMapping("/{listingId}")
	public ResponseEntity<ApiResponse<String>> addFavorite(@PathVariable Long listingId, HttpSession session) {
		Long userId = ((UserDTO)session.getAttribute("userDTO")).getId();
		userService.addFavoriteListing(userId, listingId);
		return ResponseEntity.ok(ApiResponse.success("加入關注成功", "房屋已加入關注"));
	}
	
	// 取消關注
	@DeleteMapping("/{listingId}")
	public ResponseEntity<ApiResponse<String>> removeFavorite(@PathVariable Long listingId, HttpSession session) {
		Long userId = ((UserDTO)session.getAttribute("userDTO")).getId();
		userService.removeFavoriteListing(userId, listingId);
		return ResponseEntity.ok(ApiResponse.success("取消關注成功", "房屋已取消關注"));
	}
}
