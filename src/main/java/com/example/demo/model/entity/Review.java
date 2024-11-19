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
@Table(name = "reviews")
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId; // 評價編號

	@ManyToOne
    @JoinColumn(name = "listing_id")
	@Column(nullable = false)
    private Integer reviewListingId; // 關聯的房源 ID

	@ManyToOne
    @JoinColumn(name = "user_id")
	@Column(nullable = false)
    private Integer reviewUserId; // 評價人（租客）ID

    @Column(nullable = false)
    private Integer rating; // 評分（1到5分）

    @Column(length = 500)
    private String comment; // 評價內容

    @Column(nullable = false)
    private LocalDateTime reviewCreatedAt; // 評價日期
}
