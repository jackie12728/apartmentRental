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
@Table(name = "`user`")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 使用者 ID

    @Column(nullable = false)
    private String userName; // 使用者名稱

    @Column(nullable = false, unique = true)
    private String email; // 使用者 email 帳號

    @Column(nullable = false)
    private String passwordHash; // 使用者 Hash 密碼
    
    @Column(nullable = false)
	private String salt; // 隨機鹽

    @Column(nullable = false)
    private String phoneNumber; // 使用者電話號碼

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "permission_id")
    private Permission permission; // 角色權限，租客 1、房東 2、系統管理員 3

    @Column(nullable = false)
    private LocalDateTime createdAt; // 註冊日期

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "status_id")
	private UserStatus userStatus; // 使用者狀態 (1 啟用、0 停用)
    
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Appointment> appointments;
    
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Listing> listings;
    
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Order> orders;
    
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Review> reviews;
    
}
