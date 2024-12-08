package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.ListingDTO;
import com.example.demo.model.dto.ListingImageDTO;
import com.example.demo.model.dto.RegionDTO;
import com.example.demo.model.entity.Listing;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.ListingImageRepository;
import com.example.demo.repository.ListingRepository;
import com.example.demo.repository.RegionRepository;
import com.example.demo.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private ListingRepository listingRepository;
	
	@Autowired
	private ListingImageRepository listingImageRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CityDTO> getAllCities() {
		return cityRepository.findAll().stream()
				.map(city -> modelMapper.map(city, CityDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegionDTO> getRegions(Long cityId) {
		return regionRepository.findByCityId(cityId).stream()
				.map(region -> modelMapper.map(region, RegionDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ListingDTO> getListings(Long cityId, List<Long> regionIds, Integer minRent, Integer maxRent, String listingname) {
        // 構建查詢條件
        Specification<Listing> spec = Specification.where(SearchBarSpecifications.byCityId(cityId))
                .and(SearchBarSpecifications.byRegionIds(regionIds))
                .and(SearchBarSpecifications.byRentRange(minRent, maxRent))
                .and(SearchBarSpecifications.byListingname(listingname));

        // 查詢符合條件的所有 Listing 資料
        List<Listing> listings = listingRepository.findAll(spec);

        // 將 Listing 轉換為 ListingDTO 並填充圖片路徑
        return listings.stream().map(listing -> {
            // 使用 ModelMapper 將 Listing 轉換為 ListingDTO
            ListingDTO listingDTO = modelMapper.map(listing, ListingDTO.class);

            // 根據 Listing ID 取得所有對應的圖片資料
            List<ListingImageDTO> listingImages = getListingImages(listing.getId());

            // 提取每個圖片的路徑並存入 List<String>
            List<String> imagePaths = listingImages.stream()
                    .map(ListingImageDTO::getImagePath)
                    .collect(Collectors.toList());

            // 設置圖片路徑到 ListingDTO 的 imagePaths 屬性
            listingDTO.setImagePaths(imagePaths);

            return listingDTO;
        }).collect(Collectors.toList());
    }

	@Override
	public List<ListingImageDTO> getListingImages(Long listingId) {
		return listingImageRepository.findByListingIdOrderByIdAsc(listingId).stream()
				.map(listingImage -> modelMapper.map(listingImage, ListingImageDTO.class))
				.collect(Collectors.toList());
	}

}
