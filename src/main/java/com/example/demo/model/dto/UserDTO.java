package com.example.demo.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@NotNull(message = "{userDto.id.notNull}")
	private Long id; // 使用者 ID

	@NotNull(message = "{userDto.username.notNull}")
    private String username; // 使用者名稱

	@NotNull(message = "{userDto.email.notNull}")
    private String email; // 使用者 email 帳號

	@NotNull(message = "{userDto.phoneNumber.notNull}")
    private String phoneNumber; // 使用者電話號碼

    @NotNull(message = "{userDto.permissionId.notNull}")
    private Long permissionId; // 角色權限，租客 1、房東 2、系統管理員 3

    @NotNull(message = "{userDto.createdAt.notNull}")
    private LocalDateTime createdAt; // 註冊日期

    @NotNull(message = "{userDto.statusId.notNull}")
	private Long statusId; // 使用者狀態 (1 啟用、0 停用)
}
