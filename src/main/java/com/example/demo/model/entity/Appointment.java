package com.example.demo.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "appointment")
public class Appointment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 預約編號

	@ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing; // 房源

	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 租客ID
	
	@Column(nullable = false)
    private LocalDate appointmentDate; // 預約時間編號

    @Column(nullable = false)
    private Long appointmentTime; // 預約時間編號

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule; // 預約狀態（1 待確認、2 已確認、3 已完成）

    @Column(nullable = false)
    private LocalDateTime appointmentCreatedAt; // 預約請求日期
}
