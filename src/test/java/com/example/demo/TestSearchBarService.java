package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.ListingDTO;
import com.example.demo.service.SearchService;

@SpringBootTest
public class TestSearchBarService {

	@Autowired
	private SearchService searchService;
	
	@Test
	public void test() {
		Long cityId = 1L;
		List<Long> regionIds = Arrays.asList(1L, 2L, 3L, 4L);
		Integer minRent = 100;
		Integer maxRent = 30000;
		String listingname = "台北";
		List<ListingDTO> listingDTOs = searchService.searchListings(cityId, regionIds, minRent, maxRent, listingname);
		
		System.out.print(listingDTOs);
	}
}
