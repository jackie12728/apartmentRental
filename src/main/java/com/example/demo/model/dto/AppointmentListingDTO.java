package com.example.demo.model.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentListingDTO {
	
	@NotNull(message = "{appointmentListingDTO.appointmentDate.notNull}")
	private LocalDate appointmentDate; // 預約日期
	
	@NotNull(message = "{appointmentListingDTO.appointmentTime.notNull}")
	private String appointmentTime; // 預約時間
	
	@NotNull(message = "{appointmentListingDTO.address.notNull}")
    private String address; // 房源地址
	
	@NotNull(message = "{appointmentListingDTO.listingname.notNull}")
    private String listingname; // 房源名稱
	
	@NotNull(message = "{appointmentListingDTO.scheduleName.notNull}")
	private String scheduleName; // 預約狀態名稱

}
