package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.RegionDTO;

public interface SearchService {

	public List<CityDTO> getAllCities();
	
	public List<RegionDTO> getRegions(Long cityId);
}
