package com.example.demo.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingImageDTO {

	@NotNull(message = "{listingImageDto.id.notNull}")
    private Long id; // 圖片編號

	@NotNull(message = "{listingImageDto.listingId.notNull}")
    private Long listingId; // 關聯的房源ID

	@NotNull(message = "{listingImageDto.imagePath.notNull}")
    private String imagePath; // 圖片路徑

	@NotNull(message = "{listingImageDto.uploadedAt.notNull}")
    private LocalDateTime uploadedAt; // 上傳日期
}
