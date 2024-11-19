package com.example.demo.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@NotNull(message = "{userDto.userId.notNull}")
	private Integer userId; // 使用者 ID

	@NotNull(message = "{userDto.userName.notNull}")
    private String userName; // 使用者名稱

	@NotNull(message = "{userDto.email.notNull}")
    private String email; // 使用者 email 帳號

    private String phoneNumber; // 使用者電話號碼

    @NotNull(message = "{userDto.role.notNull}")
    private Integer role; // 角色權限，租客 1、房東 2、系統管理員 3

    @NotNull(message = "{userDto.createdAt.notNull}")
    private LocalDateTime createdAt; // 註冊日期

    @NotNull(message = "{userDto.status.notNull}")
	private Integer status; // 使用者狀態 (1 啟用、0 停用)
}
