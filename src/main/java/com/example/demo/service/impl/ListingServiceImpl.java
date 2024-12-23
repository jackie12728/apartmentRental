package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ListingDTO;
import com.example.demo.model.entity.City;
import com.example.demo.model.entity.Listing;
import com.example.demo.model.entity.Region;
import com.example.demo.model.entity.Rental;
import com.example.demo.model.entity.User;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.ListingRepository;
import com.example.demo.repository.RegionRepository;
import com.example.demo.repository.RentalRepository;
import com.example.demo.service.ListingService;

import jakarta.transaction.Transactional;

@Service
public class ListingServiceImpl implements ListingService {
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	// 新增房屋
	@Override
	public Optional<ListingDTO> saveListing(ListingDTO listingDTO) {
		Listing listing = modelMapper.map(listingDTO, Listing.class);
		listing.setListingCreatedAt(LocalDateTime.now());
		listing.setUpdatedAt(LocalDateTime.now());
		listing = listingRepository.save(listing);
		
		return Optional.of(modelMapper.map(listing, ListingDTO.class));
	}

	// 修改房屋
	@Override
	public Optional<ListingDTO> modifyListing(ListingDTO listingDTO) {
		Optional<Listing> OptListing = listingRepository.findById(listingDTO.getId());
		Listing listing = modelMapper.map(OptListing, Listing.class);
		listing.setListingname(listingDTO.getListingname());
		listing.setDescription(listingDTO.getDescription());
		listing.setAddress(listingDTO.getAddress());
		listing.setRent(listingDTO.getRent());
		listing.setUpdatedAt(LocalDateTime.now());
		
		City city = cityRepository.findById(listingDTO.getCityId()).orElseThrow(() -> new RuntimeException("找不到城市"));
		listing.setCity(city);
		
		Region region = regionRepository.findById(listingDTO.getRegionId()).orElseThrow(() -> new RuntimeException("找不到區域"));
		listing.setRegion(region);
		
		Rental rental = rentalRepository.findById(listingDTO.getRentalId()).orElseThrow(() -> new RuntimeException("找不到出租狀態"));
		listing.setRental(rental);
		
		listing = listingRepository.save(listing);
		
		return Optional.of(modelMapper.map(listing, ListingDTO.class));
	}

	// 刪除房屋
//	@Override
//	@Transactional
//	public Optional<ListingDTO> deleteListing(Long listingId) {
//		Optional<Listing> OptListing = listingRepository.findById(listingId);
//	    
//	    if (OptListing.isPresent()) {
//	        Listing listing = OptListing.get();
//	        
//	        // 移除與用戶的關注關聯
//	        for (User user : listing.getFavoriteUsers()) {
//	            user.getFavoriteListings().remove(listing);
//	        }
//
//	        // 刪除房源（連帶刪除 appointments, listingImages, reviews）
//	        listingRepository.delete(listing);
//	        
//	        // 返回刪除的房源資訊
//	        return Optional.of(modelMapper.map(listing, ListingDTO.class));
//	    } else {
//	        throw new RuntimeException("找不到要刪除的房源");
//	    }
//	}
	
	@Override
	@Transactional
	public Optional<ListingDTO> deleteListing(Long listingId) {
	    // 確認房源是否存在
	    Optional<Listing> listingOptional = listingRepository.findById(listingId);
	    if (!listingOptional.isPresent()) {
	        // 如果房源不存在，回傳空的 Optional
	        return Optional.empty();
	    }

	    // 獲取房源資料
	    Listing listing = listingOptional.get();

	    // 刪除房源
	    listingRepository.delete(listing);

	    // 使用 ModelMapper 將刪除的房源轉換為 DTO
	    ListingDTO listingDTO = modelMapper.map(listing, ListingDTO.class);

	    // 回傳被刪除的房源資料
	    return Optional.of(listingDTO);
	}


}
