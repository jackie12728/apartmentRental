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
import lombok.Data;
import lombok.ToString;

@Data
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 房源編號

    @Column(nullable = false)
    private String listingName; // 房源名稱

    @Column(nullable = false)
    private String description; // 房源描述

    @Column(nullable = false)
    private String address; // 房源地址

    @Column(nullable = false)
    private Integer rent; // 房源租金

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private User user; // 房東編號 (使用者 ID)

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "rental_id")
    private Rental rental; // 狀態編號（待出租 1、已出租 2、已下架 3）

    @Column(nullable = false)
    private LocalDateTime listingCreatedAt; // 房源上架日期

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 房源更新日期
    
    @OneToMany(mappedBy = "listing")
    @ToString.Exclude
    private List<Appointment> appointments;
    
    @OneToMany(mappedBy = "listing")
    @ToString.Exclude
    private List<ListingImage> listingImages;
    
    @OneToMany(mappedBy = "listing")
    @ToString.Exclude
    private List<Order> orders;
    
    @OneToMany(mappedBy = "listing")
    @ToString.Exclude
    private List<Review> reviews;
    
}
