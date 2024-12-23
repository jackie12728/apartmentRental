package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.ListingDTO;
import com.example.demo.service.ListingService;

@SpringBootTest
public class TestSaveListing {
	@Autowired
	private ListingService listingService;
	
	@Test
	public void test() {
		String listingname = "近捷運站、麥當勞斜對面";
		String description = "邊間~ 採光佳~ 一樓永豐銀行";
		Long cityId = 1L;
		Long regionId = 160L;
		String address = "台北市士林區南京東路五段46號12樓";
		Integer rent = 8400;
		Long userId = 1L;
		Long rentalId = 1L;
		
		ListingDTO listingDTO = new ListingDTO();
		listingDTO.setListingname(listingname);
		listingDTO.setDescription(description);
		listingDTO.setCityId(cityId);
		listingDTO.setRegionId(regionId);
		listingDTO.setAddress(address);
		listingDTO.setRent(rent);
		listingDTO.setUserId(userId);
		listingDTO.setRentalId(rentalId);
		
		Optional<ListingDTO> optListing = listingService.saveListing(listingDTO);
		
		System.out.println(optListing);
	}
}
