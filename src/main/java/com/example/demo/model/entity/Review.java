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
import lombok.ToString;

@Data
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "review")
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 評價編號

	@ManyToOne
	@ToString.Exclude
    @JoinColumn(name = "listing_id")
    private Listing listing; // 關聯的房源 ID

	@ManyToOne
	@ToString.Exclude
    @JoinColumn(name = "user_id")
    private User user; // 評價人（租客）ID

    @Column(nullable = false)
    private Integer rating; // 評分（1到5分）

    @Column(length = 500, nullable = false)
    private String comment; // 評價內容

    @Column(nullable = false)
    private LocalDateTime reviewCreatedAt; // 評價日期
}
