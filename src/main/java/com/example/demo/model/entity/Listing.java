package com.example.demo.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private Integer listingId; // 房源編號

    @Column(nullable = false)
    private String listingName; // 房源名稱

    private String description; // 房源描述

    @Column(nullable = false)
    private String address; // 房源地址

    @Column(name = "rent", nullable = false)
    private Integer rent; // 房源租金

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Integer landlordId; // 房東編號 (使用者 ID)

    @ManyToOne
    @JoinColumn(name = "listing_status")
    @Column(columnDefinition = "Integer default 1")
    private Integer listingStatus; // 狀態編號（待出租 1、已出租 2、已下架 3）

    @Column(nullable = false)
    private LocalDateTime listingCreatedAt; // 房源上架日期

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 房源更新日期
    
    @OneToMany(mappedBy = "listing")
    private List<Appointment> appointments;
    
}
