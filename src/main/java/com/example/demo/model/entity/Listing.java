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
    @Column(name = "listing_status", nullable = false)
    private String listingStatus; // 狀態（待出租 1、已出租 2）

    @Column(name = "listing_created_at", nullable = false)
    private LocalDateTime listingCreatedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
