package com.example.demo;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.AppointmentDTO;
import com.example.demo.service.AppointmentService;

@SpringBootTest
public class TestSaveAppointment {

	@Autowired
	private AppointmentService appointmentService;
	
	@Test
	public void test() {
		Long listingId = 1L;
		Long userId = 1L;
		LocalDate appointmentDate = LocalDate.parse("2024-12-16");
		String appointmentTime = "21:00";
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setListingId(listingId);
		appointmentDTO.setUserId(userId);
		appointmentDTO.setAppointmentDate(appointmentDate);
		appointmentDTO.setAppointmentTime(appointmentTime);
		Optional<AppointmentDTO> aOptional = appointmentService.saveAppointment(appointmentDTO);
		System.out.println(aOptional);
		
	}
}
