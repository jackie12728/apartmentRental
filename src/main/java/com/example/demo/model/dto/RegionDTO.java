package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {

	@NotNull(message = "{regionDTO.id.notNull}")
	private Long id; // 區域 ID
	
	@NotNull(message = "{regionDTO.name.notNull}")
	private String name; // 區域名稱
}
