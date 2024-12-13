package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserIdDTO {

	@NotNull(message = "{SimpleUserIdDTO.id.notNull}")
	private Long id; // 使用者 ID
}
