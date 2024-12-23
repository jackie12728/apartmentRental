package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.dto.ListingDTO;

public interface ListingService {
	// 新增房屋
	public Optional<ListingDTO> saveListing(ListingDTO listingDTO);
	
	// 修改房屋
	public Optional<ListingDTO> modifyListing(ListingDTO listingDTO);
	
	// 刪除房屋
	public Optional<ListingDTO> deleteListing(Long listingId);
}
