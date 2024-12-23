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
	@Override
	@Transactional
	public Optional<ListingDTO> deleteListing(Long listingId) {
		// 檢查 Listing 是否存在
	    Optional<Listing> listingOptional = listingRepository.findById(listingId);
	    if (!listingOptional.isPresent()) {
	        System.out.println("Listing not found for ID: " + listingId);
	        return Optional.empty();
	    }
	    Listing listing = listingOptional.get();
	    
	    listingRepository.deleteUserListingByListingId(listingId);
	    listingRepository.deleteAppointmentByListingId(listingId);
	    listingRepository.deleteListingImageByListingId(listingId);
	    listingRepository.deleteReviewByListingId(listingId);
	    listingRepository.deleteListingByListingId(listingId);

	    // 確認刪除成功後返回
	    ListingDTO listingDTO = modelMapper.map(listing, ListingDTO.class);
	    return Optional.of(listingDTO);
	}



}
