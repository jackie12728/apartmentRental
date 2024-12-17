package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.AppointmentDTO;
import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.ListingDTO;
import com.example.demo.model.dto.ListingImageDTO;
import com.example.demo.model.dto.RegionDTO;

public interface SearchService {

	public List<CityDTO> getAllCities();
	
	public List<RegionDTO> getRegions(Long cityId);
	
	public List<ListingDTO> getListings(Long cityId, List<Long> regionIds, Integer minRent, Integer maxRent, String listingname);
	
	public List<ListingImageDTO> getListingImages(Long listingId);
	
	public List<AppointmentDTO> getUserAppointments(Long userId);
	
}
