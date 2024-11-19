package com.example.demo.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
	
	@NotNull(message = "{appointmentDto.appointmentId.notNull}")
	private Long appointmentId; // 預約編號
	
	@NotNull(message = "{appointmentDto.appointmentListingId.notNull}")
	private Long appointmentListingId; // 房源ID
	
	@NotNull(message = "{appointmentDto.appointmentUserId.notNull}")
	private Long appointmentUserId; // 租客ID
	
	@NotNull(message = "{appointmentDto.appointmentDate.notNull}")
	private LocalDateTime appointmentDate; // 預約日期時間
	
	@NotNull(message = "{appointmentDto.appointmentStatus.notNull}")
	private Integer appointmentStatus; // 預約狀態（1 待確認、2 已確認、3 已完成、4 已取消）
	
	@NotNull(message = "{appointmentDto.appointmentCreatedAt.notNull}")
	private LocalDateTime appointmentCreatedAt; // 預約請求日期
}
