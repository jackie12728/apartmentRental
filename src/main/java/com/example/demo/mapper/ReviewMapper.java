package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ReviewDTO;
import com.example.demo.model.entity.Review;

@Component // 此元件由 Springboot 自動掃描後管理
public class ReviewMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ReviewDTO toDto(Review review) {
		// Entity 轉 DTO
		return modelMapper.map(review, ReviewDTO.class);
	}
	
	public Review toEntity(ReviewDTO reviewDto) {
		// DTO 轉 Entity
		return modelMapper.map(reviewDto, Review.class);
	}
}
