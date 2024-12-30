package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.ListingDTO;
import com.example.demo.service.ListingService;

@SpringBootTest
public class TestModifyListing {
	@Autowired
	private ListingService listingService;
	
	@Test
	public void test() {
		Long id = 42L;
		String listingname = "台北市精緻小套房";
		String description = "小巧舒適，適合單身族群，近捷運站";
		Long cityId = 1L;
		Long regionId = 161L;
		String address = "台北市中山區長安東路二段67號";
		Integer rent = 6600;
		Long userId = 1L;
		Long rentalId = 2L;
		
		ListingDTO listingDTO = new ListingDTO();
		listingDTO.setId(id);
		listingDTO.setListingname(listingname);
		listingDTO.setDescription(description);
		listingDTO.setCityId(cityId);
		listingDTO.setRegionId(regionId);
		listingDTO.setAddress(address);
		listingDTO.setRent(rent);
		listingDTO.setUserId(userId);
		listingDTO.setRentalId(rentalId);
		
		Optional<ListingDTO> optListing = listingService.modifyListing(listingDTO);
		
		System.out.println(optListing);
	}
}
