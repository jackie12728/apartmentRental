package com.example.demo.model.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

	@NotNull(message = "{reviewDto.reviewId.notNull}")
	private Integer reviewId; // 評價編號

	@NotNull(message = "{reviewDto.reviewListingId.notNull}")
    private Integer reviewListingId; // 關聯的房源 ID

	@NotNull(message = "{reviewDto.reviewUserId.notNull}")
    private Integer reviewUserId; // 評價人（租客）ID

	@NotNull(message = "{reviewDto.rating.notNull}")
	@Range(min = 1, max = 5, message = "{reviewDto.rating.range}")
    private Integer rating; // 評分（1到5分）

	@NotNull(message = "{reviewDto.comment.notNull}")
	@Size(min = 1, max = 500, message = "{reviewDto.comment.size}")
    private String comment; // 評價內容

	@NotNull(message = "{reviewDto.reviewCreatedAt.notNull}")
    private LocalDateTime reviewCreatedAt; // 評價日期
}
