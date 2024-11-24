package com.example.demo.model.dto;

import com.example.demo.model.entity.Permission;

public class UserCert {

	private Long userId; // 使用者ID
	private String userName; // 使用者名稱
	private Permission permission; // 角色權限
	
	public UserCert(Long userId, String userName, Permission permission) {
		this.userId = userId;
		this.userName = userName;
		this.permission = permission;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Permission getPermission() {
		return permission;
	}

	@Override
	public String toString() {
		return "UserCert [userId=" + userId + ", userName=" + userName + ", permission=" + permission + "]";
	}
	
}
