package com.example.demo.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
	
	private Long id; // 預約編號
	
	@NotNull(message = "{appointmentDto.listingId.notNull}")
	private Long listingId; // 房源ID
	
	@NotNull(message = "{appointmentDto.userId.notNull}")
	private Long userId; // 租客ID
	
	@NotNull(message = "{appointmentDto.appointmentDate.notNull}")
	private LocalDate appointmentDate; // 預約日期
	
	@NotNull(message = "{appointmentDto.appointmentTime.notNull}")
	private String appointmentTime; // 預約時間
	
	@NotNull(message = "{appointmentDto.scheduleId.notNull}")
	private Integer scheduleId; // 預約狀態（1 待確認、2 已確認、3 已完成、4 已取消）
	
	@NotNull(message = "{appointmentDto.appointmentCreatedAt.notNull}")
	private LocalDateTime appointmentCreatedAt; // 預約請求日期
}
