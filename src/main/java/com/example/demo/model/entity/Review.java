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
	@Column(name = "review_id")
    private Integer reviewId; // 評價編號

	@ManyToOne
    @JoinColumn(name = "listing_id")
	@Column(name = "review_listing_id", nullable = false)
    private Integer reviewListingId; // 關聯的房源 ID

	@ManyToOne
    @JoinColumn(name = "user_id")
	@Column(name = "tenant_id", nullable = false)
    private Integer tenantId; // 評價人（租客）ID

    @Column(name = "rating", nullable = false)
    private Integer rating; // 評分（1到5分）

    @Column(name = "comment", length = 500)
    private String comment; // 評價內容

    @Column(name = "review_created_at", nullable = false)
    private LocalDateTime reviewCreatedAt; // 評價日期
}
