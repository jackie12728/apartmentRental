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
import lombok.Data;

@Data
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "`order`")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 訂單編號

	@ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing; // 房源ID

	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 租客ID

    @Column(nullable = false)
    private LocalDateTime orderDate; // 訂單日期時間

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment; // 訂單狀態 (1 未付款、2 已付款、0 已取消)

    @Column(nullable = false)
    private String amount; // 訂單金額
    
}
