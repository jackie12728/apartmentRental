package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ListingImageDto;
import com.example.demo.model.entity.Listing;
import com.example.demo.model.entity.ListingImage;

@Component // 此元件由 Springboot 自動掃描後管理
public class ListingImageMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ListingImageDto toDto(ListingImage listingImage) {
		// Entity 轉 DTO
		return modelMapper.map(listingImage, ListingImageDto.class);
	}
	
	public Listing toEntity(ListingImageDto listingImageDto) {
		// DTO 轉 Entity
		return modelMapper.map(listingImageDto, Listing.class);
	}
}
