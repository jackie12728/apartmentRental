package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.ListingDTO;
import com.example.demo.model.dto.ListingImageDTO;
import com.example.demo.model.dto.RegionDTO;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.SearchService;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /search
 * ----------------------------------
 * GET /city                     查詢所有城市
 * GET /region/{cityId}          查詢城市的所有區域
 * GET /searchBar                依據搜尋列條件查詢房屋
 * GET /listingImage/{listingId} 依據房屋ID查詢房屋圖片
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
		if(cities.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "查詢不到城市"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("查詢城市清單成功", cities));
	}
	
	@GetMapping("/region/{cityId}")
	public ResponseEntity<ApiResponse<List<RegionDTO>>> grtRegions(@PathVariable Long cityId) {
		List<RegionDTO> regions = searchService.getRegions(cityId);
		if(regions.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "查詢不到城市的所屬區域"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("查詢城市所屬區域成功", regions));
	}
	
    @GetMapping("/searchBar")
    public ResponseEntity<ApiResponse<List<ListingDTO>>> getListings(
        @RequestParam(required = false) Long cityId,
        @RequestParam(required = false) List<Long> regionIds, // 接收多個 regionId
        @RequestParam(required = false) Integer minRent,
        @RequestParam(required = false) Integer maxRent,
        @RequestParam(required = false) String listingName
    ) {
        List<ListingDTO> listings = searchService.getListings(cityId, regionIds, minRent, maxRent, listingName);
        if(listings.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "查詢不到符合條件的房屋"));
		}
        return ResponseEntity.ok(ApiResponse.success("查詢成功",listings));
    }
    
    @GetMapping("/listingImage/{listingId}")
    public ResponseEntity<ApiResponse<List<ListingImageDTO>>> getListingImages(@PathVariable Long listingId) {
    	List<ListingImageDTO> listingImages = searchService.getListingImages(listingId);
    	if(listingImages.isEmpty()) {
    		return ResponseEntity.status(404).body(ApiResponse.error(404, "查詢不到房屋的圖片"));
    	}
    	
    	return ResponseEntity.ok(ApiResponse.success("查詢房屋圖片成功", listingImages));
    }
    
}
