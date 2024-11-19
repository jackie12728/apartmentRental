package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "rental_status")
public class RentalStatus {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId; // 房源狀態 ID

    @Column(nullable = false)
    private String rentalName; // 房源狀態名稱
    
}
