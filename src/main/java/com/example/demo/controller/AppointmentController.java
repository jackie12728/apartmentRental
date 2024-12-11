package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aop.CheckUserSession;
import com.example.demo.model.dto.AppointmentDTO;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AppointmentService;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /appoint
 * ----------------------------------
 * GET  /{listingId} 依據房屋ID查詢預約時間
 * POST /appointment 儲存預約
 * */

@RestController
@RequestMapping("/appoint")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	// 取得預約時間
	@GetMapping("/{listingId}")
//	@CheckUserSession
	public ResponseEntity<ApiResponse<List<AppointmentDTO>>> getAppointments(@PathVariable Long listingId) {
		List<AppointmentDTO> appointmentDTOs = appointmentService.getAppointments(listingId);
		if(appointmentDTOs.isEmpty()) {
			return ResponseEntity.ok(ApiResponse.success("沒有預約紀錄", appointmentDTOs));
		}
		
		return ResponseEntity.ok(ApiResponse.success("查詢預約成功", appointmentDTOs));
	}
	
	// 儲存預約
	@PostMapping("/appointment")
	@CheckUserSession
	public ResponseEntity<ApiResponse<String>> saveAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		Optional<AppointmentDTO> optAppointmentDTO = appointmentService.saveAppointment(appointmentDTO);
		if(optAppointmentDTO.isEmpty()) {
			return ResponseEntity.status(400).body(ApiResponse.error(400, "預約失敗"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("預約成功", "預約成功"));
	}
	
}
