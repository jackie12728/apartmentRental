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
@Table(name = "orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
    private Long orderId; // 訂單編號

	@ManyToOne
    @JoinColumn(name = "listing_id")
	@Column(name = "order_listing_id", nullable = false)
    private Long orderListingId; // 房源ID

	@ManyToOne
    @JoinColumn(name = "user_id")
	@Column(name = "order_user_id", nullable = false)
    private Long orderUserId; // 租客ID

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate; // 訂單日期時間

    @ManyToOne
    @JoinColumn(name = "payment_id")
    @Column(name = "order_status", columnDefinition = "Integer default 1")
    private Integer orderStatus; // 訂單狀態 (1 未付款、2 已付款、0 已取消)

    @Column(name = "amount", nullable = false)
    private String amount; // 訂單金額
    
}
