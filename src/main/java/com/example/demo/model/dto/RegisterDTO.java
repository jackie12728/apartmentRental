package com.example.demo.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

	@NotNull(message = "{registerDTO.userName.notNull}")
    private String userName; // 使用者名稱

	@NotNull(message = "{registerDTO.email.notNull}")
    private String email; // 使用者 email 帳號

	@NotNull(message = "{registerDTO.password.notNull}")
    private String password; // 使用者密碼
	
	@NotNull(message = "{registerDTO.phoneNumber.notNull}")
    private String phoneNumber; // 使用者電話號碼
    
}
