package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.ListingDTO;
import com.example.demo.service.ListingService;

@SpringBootTest
public class TestDeleteListing {
	@Autowired
	private ListingService listingService;
	
	@Test
	public void test() {
		Long listingId = 43L;
		Optional<ListingDTO> optListing = listingService.deleteListing(listingId);
		System.out.println("optListing: " + optListing);
	}
}
