package com.example.demo.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "`user`")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 使用者 ID

	@Column(nullable = false)
	private String username; // 使用者名稱

	@Column(nullable = false, unique = true)
	private String email; // 使用者 email 帳號

	@Column(nullable = false)
	private String passwordHash; // 使用者 Hash 密碼

	@Column(nullable = false)
	private String salt; // 隨機鹽

	@Column(nullable = false)
	private String phoneNumber; // 使用者電話號碼

	@ManyToOne
	@JoinColumn(name = "permission_id")
	private Permission permission; // 角色權限，租客 1、房東 2、系統管理員 3

	@Column(nullable = false)
	private LocalDateTime createdAt; // 註冊日期

	@ManyToOne
	@JoinColumn(name = "status_id")
	private UserStatus userStatus; // 使用者狀態 (1 啟用、0 停用)

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Listing> listings;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Review> reviews;

	// 建立用戶可以關注商品的多對多關係
	@ManyToMany()
	@JoinTable(name = "user_listing", // 關聯表名稱
			joinColumns = @JoinColumn(name = "user_id"), // 用戶外鍵
			inverseJoinColumns = @JoinColumn(name = "listing_id") // 房屋外鍵
	)
	private Set<Listing> favoriteListings;
	
	/*
     * hashCode 方法中出現了遞迴循環，通常是因為 User 和 Product 實體之間的雙向關聯造成的，Hibernate 無法處理這種循環依賴。
     * 所以要自行實現
     * */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
