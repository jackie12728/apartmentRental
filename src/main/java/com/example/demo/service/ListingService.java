package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.dto.ListingDTO;

public interface ListingService {
	// 新增房屋
	public Optional<ListingDTO> SaveListing(ListingDTO listingDTO);
	
	// 修改房屋
	public Optional<ListingDTO> ModifyListing(ListingDTO listingDTO);
	
	// 刪除房屋
	public Optional<ListingDTO> DeleteListing(ListingDTO listingDTO);
}
