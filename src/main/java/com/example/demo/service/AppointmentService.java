package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.dto.AppointmentDTO;

public interface AppointmentService {

	public List<AppointmentDTO> getAppointments(Long listingId);
	
	public void saveAppointment(Long listingId, Long userId, LocalDate appointmentDate, Long appointmentTime);
}
