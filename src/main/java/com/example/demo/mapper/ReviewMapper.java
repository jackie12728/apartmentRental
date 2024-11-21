package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ReviewDto;
import com.example.demo.model.entity.Review;

@Component // 此元件由 Springboot 自動掃描後管理
public class ReviewMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ReviewDto toDto(Review review) {
		// Entity 轉 DTO
		return modelMapper.map(review, ReviewDto.class);
	}
	
	public Review toEntity(ReviewDto reviewDto) {
		// DTO 轉 Entity
		return modelMapper.map(reviewDto, Review.class);
	}
}
