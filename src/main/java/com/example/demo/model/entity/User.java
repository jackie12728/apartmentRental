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
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId; // 使用者 ID

    @Column(name = "username", nullable = false)
    private String username; // 使用者名稱

    @Column(name = "email", nullable = false, unique = true)
    private String email; // 使用者 email 帳號

    @Column(name = "password_hash", nullable = false)
    private String passwordHash; // 使用者 Hash 密碼
    
    @Column(name = "salt", nullable = false)
	private String salt; // 隨機鹽

    @Column(name = "phone_number")
    private String phoneNumber; // 使用者電話號碼

    @ManyToOne
    @JoinColumn(name = "role_id")
    @Column(name = "role", columnDefinition = "Integer default 1")
    private Integer role; // 角色權限，租客 1、房東 2、系統管理員 3

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 註冊日期

    @ManyToOne
    @JoinColumn(name = "status_id")
    @Column(name = "status", columnDefinition = "Integer default 1")
	private Integer status; // 使用者狀態 (1 啟用、0 停用)
    
}
