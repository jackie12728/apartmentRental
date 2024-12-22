package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteListingDTO {
	private Long id;
	private String listingname;
	private String address; // 房源地址
	private Integer rent; // 房源租金
}
