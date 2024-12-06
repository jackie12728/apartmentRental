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
		Specification<Listing> spec = Specification.where(SearchBarSpecifications.byCityId(cityId))
	            .and(SearchBarSpecifications.byRegionIds(regionIds))
	            .and(SearchBarSpecifications.byRentRange(minRent, maxRent))
	            .and(SearchBarSpecifications.byListingname(listingname));
	        
		return listingRepository.findAll(spec).stream()
				.map(search -> modelMapper.map(search, ListingDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ListingImageDTO> getListingImages(Long listingId) {
		return listingImageRepository.findByListingIdOrderByIdAsc(listingId).stream()
				.map(listingImage -> modelMapper.map(listingImage, ListingImageDTO.class))
				.collect(Collectors.toList());
	}

}
