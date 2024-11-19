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
@Table(name = "payment_status")
public class PaymentStatus {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId; // 訂單狀態 ID

    @Column(nullable = false)
    private String paymentName; // 訂單狀態名稱
    
}
