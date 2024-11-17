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

/**
CREATE TABLE listings (
    listing_id INT AUTO_INCREMENT PRIMARY KEY,             -- 房源編號 (主鍵，自增)
    title VARCHAR(255) NOT NULL,                           -- 房源標題 (不可為 NULL)
    description TEXT,                                      -- 房源描述 (可為 NULL)
    address VARCHAR(255) NOT NULL,                         -- 地址 (不可為 NULL)
    price DECIMAL(10, 2) NOT NULL,                         -- 租金 (不可為 NULL)，保留 2 位小數
    landlord_id INT NOT NULL,                              -- 房東 ID (外鍵)
    listing_status VARCHAR(255) NOT NULL,                  -- 房源狀態 (不可為 NULL)
    listing_created_at DATETIME NOT NULL,                  -- 房源創建時間 (不可為 NULL)
    updated_at DATETIME,                                   -- 更新時間 (可為 NULL)
    FOREIGN KEY (landlord_id) REFERENCES users(user_id)    -- 外鍵約束，與 users 表的 user_id 關聯
);

 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_id")
    private Integer listingId; // 房源編號

    @Column(name = "title", nullable = false)
    private String title; // 房源標題

    @Column(name = "description")
    private String description; // 房源描述

    @Column(name = "address", nullable = false)
    private String address; // 地址

    @Column(name = "price", nullable = false)
    private Double price; // 租金

    @ManyToOne
    @JoinColumn(name = "landlord_id", nullable = false)
    private User landlord; // 房東 (使用者 ID)

    @ManyToOne
    @JoinColumn(name = "listing_status", nullable = false)
    private String listingStatus; // 狀態（待出租 1、已出租 2）

    @Column(name = "listing_created_at", nullable = false)
    private LocalDateTime listingCreatedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
