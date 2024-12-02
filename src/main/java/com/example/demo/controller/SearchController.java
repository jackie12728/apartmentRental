package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.RegionDTO;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.SearchService;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /search
 * ----------------------------------
 * GET /city            查詢所有城市
 * GET /region/{cityId} 查詢城市的所有區域
 */

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@GetMapping("/city")
	public ResponseEntity<ApiResponse<List<CityDTO>>> getCities() {
		List<CityDTO> cities = searchService.getAllCities();
		return ResponseEntity.ok(ApiResponse.success("獲取城市清單", cities));
	}
	
	@GetMapping("/region/{cityId}")
	public ResponseEntity<ApiResponse<List<RegionDTO>>> grtRegions(@PathVariable Long cityId) {
		List<RegionDTO> regions = searchService.getRegions(cityId);
		if(regions.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "查詢不到城市的所屬區域"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("查詢成功", regions));
	}
}
