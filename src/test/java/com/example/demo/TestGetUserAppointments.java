package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.AppointmentListingDTO;
import com.example.demo.service.SearchService;

@SpringBootTest
public class TestGetUserAppointments {

	@Autowired
	private SearchService searchService;
	
	@Test
	public void test() {
		Long userId = 1L;
		List<AppointmentListingDTO> appointmentDTOs = searchService.getUserAppointments(userId);
		System.out.println(appointmentDTOs);
	}
}
