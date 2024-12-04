package com.example.demo;

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
		Long regionId = 1L;
		Integer minRent = 100;
		Integer maxRent = 30000;
		String listingname = "台北";
		List<ListingDTO> listingDTOs = searchService.searchListings(cityId, regionId, minRent, maxRent, listingname);
		
		System.out.print(listingDTOs);
	}
}
