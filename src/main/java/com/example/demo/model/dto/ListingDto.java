package com.example.demo.model.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingDto {

	@NotNull(message = "{listingDto.listingId.notNull}")
    private Integer listingId; // 房源編號

	@NotNull(message = "{listingDto.listingName.notNull}")
    private String listingName; // 房源名稱

    private String description; // 房源描述

	@NotNull(message = "{listingDto.address.notNull}")
    private String address; // 房源地址

	@NotNull(message = "{listingDto.rent.notNull}")
	@Range(min = 1, message = "{listingDto.rent.range}")
    private Integer rent; // 房源租金

	@NotNull(message = "{listingDto.landlordId.notNull}")
    private Integer landlordId; // 房東編號 (使用者 ID)

	@NotNull(message = "{listingDto.listingStatus.notNull}")
    private Integer listingStatus; // 狀態編號（待出租 1、已出租 2、已下架 3）

	@NotNull(message = "{listingDto.listingCreatedAt.notNull}")
    private LocalDateTime listingCreatedAt; // 房源上架日期

	@NotNull(message = "{listingDto.updatedAt.notNull}")
    private LocalDateTime updatedAt; // 房源更新日期
}
