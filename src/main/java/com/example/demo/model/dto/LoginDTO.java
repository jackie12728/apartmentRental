package com.example.demo.model.dto;

import lombok.Data;

@Data
public class LoginDTO {

	private String email;
	private String password;
	private Boolean isLoggedIn; // 是否登入成功 ?
	
}
