package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserDTO {

	@NotNull(message = "{SimpleUserDTO.id.notNull}")
	private Long id; // 使用者 ID
	
	@NotNull(message = "{SimpleUserDTO.username.notNull}")
	private String username; // 使用者名稱
	
	@NotNull(message = "{SimpleUserDTO.phoneNumber.notNull}")
	private String phoneNumber; // 使用者電話號碼
	
	@NotNull(message = "{SimpleUserDTO.permissionId.notNull}")
	private Long permissionId; // 角色權限
}
