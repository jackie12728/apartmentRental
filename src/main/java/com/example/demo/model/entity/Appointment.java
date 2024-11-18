package com.example.demo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "appointments")
public class Appointment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
    private Long appointmentId; // 預約編號

	@ManyToOne
    @JoinColumn(name = "listing_id")
	@Column(name = "appointment_listing_id", nullable = false)
    private Long appointmentListingId; // 房源ID

	@ManyToOne
    @JoinColumn(name = "user_id")
	@Column(name = "appointment_user_id", nullable = false)
    private Long appointmentUserId; // 租客ID

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate; // 預約日期時間

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @Column(name = "appointment_status", columnDefinition = "Integer default 1")
    private Integer appointmentStatus; // 預約狀態（1 待確認、2 已確認、3 已完成、4 已取消）

    @Column(name = "appointment_created_at", nullable = false)
    private LocalDateTime appointmentCreatedAt; // 預約請求日期
}
