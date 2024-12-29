package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.dto.ListingDTO;
import com.example.demo.model.entity.City;
import com.example.demo.model.entity.Listing;
import com.example.demo.model.entity.ListingImage;
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
	@Transactional
	public Optional<ListingDTO> saveListing(ListingDTO listingDTO) {
        // 創建並儲存 Listing
        final Listing savedListing = saveListing(modelMapper.map(listingDTO, Listing.class));
        
        // 儲存圖片資訊
        List<ListingImage> listingImages = saveListingImages(listingDTO.getImagePaths(), savedListing);
        
        // 設定雙向關聯
        savedListing.setListingImages(listingImages);
        
        return Optional.of(modelMapper.map(savedListing, ListingDTO.class));
    }
    
    private Listing saveListing(Listing listing) {
        listing.setListingCreatedAt(LocalDateTime.now());
        listing.setUpdatedAt(LocalDateTime.now());
        return listingRepository.save(listing);
    }
    
    private List<ListingImage> saveListingImages(List<String> imagePaths, Listing listing) {
        return imagePaths.stream()
            .map(imagePath -> {
                ListingImage listingImage = new ListingImage();
                listingImage.setListing(listing);
                listingImage.setImagePath(imagePath);
                listingImage.setUploadedAt(LocalDateTime.now());
                return listingImage;
            })
            .collect(Collectors.toList());
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
