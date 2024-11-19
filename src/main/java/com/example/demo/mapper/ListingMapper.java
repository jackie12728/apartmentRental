package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ListingDto;
import com.example.demo.model.entity.Listing;

@Component // 此元件由 Springboot 自動掃描後管理
public class ListingMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ListingDto toDto(Listing listing) {
		// Entity 轉 DTO
		return modelMapper.map(listing, ListingDto.class);
	}
	
	public Listing toEntity(ListingDto listingDto) {
		// DTO 轉 Entity
		return modelMapper.map(listingDto, Listing.class);
	}
}
