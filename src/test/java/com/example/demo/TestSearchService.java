package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.ListingDTO;
import com.example.demo.model.dto.ListingImageDTO;
import com.example.demo.service.SearchService;

@SpringBootTest
public class TestSearchService {

	@Autowired
	private SearchService searchService;
	
	@Test
	public void test() {
		Long cityId = 1L;
		List<Long> regionIds = Arrays.asList(159L, 160L, 3L, 4L);
		Integer minRent = 100;
		Integer maxRent = 30000;
		String listingname = "臺北";
		List<ListingDTO> listingDTOs = searchService.getListings(cityId, regionIds, minRent, maxRent, listingname);
		
		System.out.println(listingDTOs);
	}
	
}
