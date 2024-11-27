package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// 預設對自動實現 CRUD
	// 用 email 查詢 user (自動產生 SQL)
	Optional<User> findByEmail(String email);
	
}
