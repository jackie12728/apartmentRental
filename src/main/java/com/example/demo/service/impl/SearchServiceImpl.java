package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.RegionDTO;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.RegionRepository;
import com.example.demo.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
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

}
