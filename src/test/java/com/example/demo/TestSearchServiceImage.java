package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.ListingImageDTO;
import com.example.demo.service.SearchService;

@SpringBootTest
public class TestSearchServiceImage {

	@Autowired
	private SearchService searchService;
	
	@Test
	public void test() {
		Long listingId = 21L;
		List<ListingImageDTO> listingImageDTOs = searchService.getListingImages(listingId);
		System.out.println(listingImageDTOs);
	}
}
