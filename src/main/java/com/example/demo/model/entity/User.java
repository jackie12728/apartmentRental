package com.example.demo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
SQL:
CREATE TABLE apartment.users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    role INT DEFAULT 1,
    created_at TIMESTAMP NOT NULL,
    active BOOLEAN DEFAULT true
);
 */

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

    @Column(name = "role", columnDefinition = "Integer default 1")
    private String role; // 角色權限，租客 1、房東 2、系統管理員 3

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 註冊日期

    @Column(name = "active", columnDefinition = "boolean default true")
	private Boolean active; // 帳號啟動，啟動 true、停用 false
}
