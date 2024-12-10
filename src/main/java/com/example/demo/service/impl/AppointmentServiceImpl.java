package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.AppointmentDTO;
import com.example.demo.model.entity.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<AppointmentDTO> getAppointments(Long listingId) {
		return appointmentRepository.findByListingId(listingId).stream()
				.map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<AppointmentDTO> saveAppointment(AppointmentDTO appointmentDTO) {
		appointmentDTO.setScheduleId(1);
		appointmentDTO.setAppointmentCreatedAt(LocalDateTime.now());
		Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
		appointment = appointmentRepository.save(appointment);
		
		return Optional.of(modelMapper.map(appointment, AppointmentDTO.class));
	}

}
