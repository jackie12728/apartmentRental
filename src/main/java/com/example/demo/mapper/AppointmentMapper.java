package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.AppointmentDto;
import com.example.demo.model.entity.Appointment;

@Component // 此元件由 Springboot 自動掃描後管理
public class AppointmentMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public AppointmentDto toDto(Appointment appointment) {
		// Entity 轉 DTO
		return modelMapper.map(appointment, AppointmentDto.class);
	}
	
	public Appointment toEntity(AppointmentDto appointmentDto) {
		// DTO 轉 Entity
		return modelMapper.map(appointmentDto, Appointment.class);
	}
}
