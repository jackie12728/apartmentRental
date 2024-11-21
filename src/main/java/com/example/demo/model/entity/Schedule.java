package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "schedule")
public class Schedule {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 預約狀態 ID

    @Column(nullable = false)
    private String scheduleName; // 預約狀態名稱
    
    @OneToMany(mappedBy = "schedule")
    private List<Appointment> appointments;
    
}
