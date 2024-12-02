package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

	@NotNull(message = "{cityDTO.id.notNull}")
	private Long id; // 縣市 ID
	
	@NotNull(message = "{cityDTO.name.notNull}")
	private String name; // 縣市名稱
}
