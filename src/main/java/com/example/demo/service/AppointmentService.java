package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.dto.AppointmentDTO;

public interface AppointmentService {

	public List<AppointmentDTO> getAppointments(Long listingId);
	
	public Optional<AppointmentDTO> saveAppointment(AppointmentDTO appointmentDTO);
}
