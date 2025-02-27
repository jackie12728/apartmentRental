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

import com.example.demo.aop.CheckUserSession;
import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.RegisterDTO;
import com.example.demo.model.dto.SimpleUserDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.dto.VerifyDTO;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /auth
 * ----------------------------------
 * POST /login                 登入
 * POST /register              註冊
 * GET  /logout                登出
 * GET  /isLoggedIn            判斷目前的連線是否有登入
 * GET  /getCurrentUser        取得當前使用者的 ID、名稱、電話號碼、權限
 * POST /updateUserPhoneNumber 更新使用者電話號碼
 * POST /sendVerification      寄送驗證碼
 * POST /validateCode          驗證驗證碼
 * POST /forget/password       忘記密碼
 * */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

	@Autowired
	private UserService userService;
	
	// 登入
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
	
	// 註冊
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody RegisterDTO registerDTO) {
		
		Optional<UserDTO> optUserDTO = userService.saveUser(registerDTO);
		if(optUserDTO.isEmpty()) {
			return ResponseEntity.status(409).body(ApiResponse.error(409, "註冊重複帳號"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("註冊成功", null));
	}
	
	// 登出
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
		session.invalidate(); // session 失效
		return ResponseEntity.ok(ApiResponse.success("登出結果", "登出成功"));
	}
	
	// 檢查登入狀態
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
	
	// 取得當前使用者
	@GetMapping("/getCurrentUser")
	public ResponseEntity<ApiResponse<SimpleUserDTO>> getCurrentUser(HttpSession session) {
	    UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
	    if (userDTO == null) {
	        return ResponseEntity.status(401).body(ApiResponse.error(401, "未登入"));
	    }

	    SimpleUserDTO simpleUserIdDTO = new SimpleUserDTO(userDTO.getId(), userDTO.getUsername(), userDTO.getPhoneNumber(), userDTO.getPermissionId());
	    return ResponseEntity.ok(ApiResponse.success("取得當前用戶成功", simpleUserIdDTO));
	}
	
	// 更新使用者電話號碼
	@PostMapping("/updateUserPhoneNumber")
	@CheckUserSession
	public ResponseEntity<ApiResponse<String>> updateUserPhoneNumber(@RequestBody SimpleUserDTO simpleUserDTO) {
		Optional<UserDTO> optUserDTO = userService.updateUserPhoneNumber(simpleUserDTO);
		
		if(optUserDTO.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "更新電話號碼失敗"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("更新電話號碼成功", "更新電話號碼成功"));
	}
	
	// 寄送驗證碼
	@PostMapping("/sendVerification")
	public ResponseEntity<ApiResponse<String>> sendVerification(@RequestBody String recipientEmail) {
		Boolean sendBoolean = userService.sendVerificationCode(recipientEmail);
		
		if(sendBoolean) {
			return ResponseEntity.ok(ApiResponse.success("驗證碼寄送成功", "驗證碼寄送成功"));
		}
		
		return ResponseEntity.status(404).body(ApiResponse.error(404, "驗證碼寄送失敗"));
	}

	// 驗證驗證碼
	@PostMapping("/validateCode")
	public ResponseEntity<ApiResponse<String>> validateCode(@RequestBody VerifyDTO verifyDTO) {
		Boolean verifyBoolean = userService.validateCode(verifyDTO.getEmail(), verifyDTO.getCode());
		
		if(verifyBoolean) {
			return ResponseEntity.ok(ApiResponse.success("驗證碼正確", "驗證碼正確"));
		}
		
		return ResponseEntity.status(404).body(ApiResponse.error(404, "驗證碼錯誤"));
	}
}
