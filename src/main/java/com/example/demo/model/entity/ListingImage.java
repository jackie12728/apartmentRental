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
@Table(name = "listing_images")
public class ListingImage {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId; // 圖片編號

	@ManyToOne
    @JoinColumn(name = "listing_id")
	@Column(nullable = false)
    private Integer imageListingId; // 關聯的房源ID

    @Column(nullable = false)
    private String imagePath; // 圖片路徑

    @Column(nullable = false)
    private LocalDateTime uploadedAt; // 上傳日期
}
