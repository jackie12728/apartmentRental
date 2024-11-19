package com.example.demo.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingImageDto {

	@NotNull(message = "{listingImageDto.imageId.notNull}")
    private Integer imageId; // 圖片編號

	@NotNull(message = "{listingImageDto.imageListingId.notNull}")
    private Integer imageListingId; // 關聯的房源ID

	@NotNull(message = "{listingImageDto.imagePath.notNull}")
    private String imagePath; // 圖片路徑

	@NotNull(message = "{listingImageDto.uploadedAt.notNull}")
    private LocalDateTime uploadedAt; // 上傳日期
}
