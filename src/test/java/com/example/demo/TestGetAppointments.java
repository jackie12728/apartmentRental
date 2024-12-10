package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.dto.AppointmentDTO;
import com.example.demo.service.AppointmentService;

@SpringBootTest
public class TestGetAppointments {

	@Autowired
	private AppointmentService appointmentService;
	
	@Test
	public void test() {
		Long listingId = 1L;
		List<AppointmentDTO> appointmentDTOs = appointmentService.getAppointments(listingId);
		System.out.println(appointmentDTOs);
	}
}
