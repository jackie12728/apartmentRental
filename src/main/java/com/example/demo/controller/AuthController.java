package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.dto.SimpleUserDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /auth
 * ----------------------------------
 * POST /login          登入
 * POST /register       註冊
 * GET  /logout         登出
 * GET  /isLoggedIn     判斷目前的連線是否有登入
 * GET  /getCurrentUser 取得當前使用者的 ID、名稱
 * */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginDTO>> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
		
		// login 判斷比對
		Optional<UserDTO> optUserDTO = userService.login(loginDTO);
		if(optUserDTO.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "登入失敗"));
		}
		
		// 存入 HttpSession 中
		session.setAttribute("userDTO", optUserDTO.get());
		return ResponseEntity.ok(ApiResponse.success("登入成功", null));
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterDTO registerDTO) {
		
		Optional<UserDTO> optUserDTO = userService.saveUser(registerDTO);
		if(optUserDTO.isEmpty()) {
			return ResponseEntity.status(409).body(ApiResponse.error(409, "註冊重複帳號"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("註冊成功", "註冊成功"));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
		session.invalidate(); // session 失效
		return ResponseEntity.ok(ApiResponse.success("登出結果", "登出成功"));
	}
	
	@GetMapping("/isLoggedIn")
	public ResponseEntity<ApiResponse<LoginDTO>> isLoggedIn(HttpSession session) {
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		LoginDTO loginDTO = new LoginDTO();
		if(userDTO == null) {
			loginDTO.setIsLoggedIn(false);
			return ResponseEntity.ok(ApiResponse.success("無登入資訊", loginDTO));
		} 
		loginDTO.setIsLoggedIn(true);
		return ResponseEntity.ok(ApiResponse.success("此人已登入資訊", loginDTO));
	}
	
	// 取得當前使用者的 ID
	@GetMapping("/getCurrentUser")
	public ResponseEntity<ApiResponse<SimpleUserDTO>> getCurrentUser(HttpSession session) {
	    UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
	    if (userDTO == null) {
	        return ResponseEntity.status(401).body(ApiResponse.error(401, "未登入"));
	    }

	    SimpleUserDTO simpleUserIdDTO = new SimpleUserDTO(userDTO.getId(), userDTO.getUsername(), userDTO.getPhoneNumber(), userDTO.getPermissionId());
	    return ResponseEntity.ok(ApiResponse.success("取得當前用戶成功", simpleUserIdDTO));
	}

}
