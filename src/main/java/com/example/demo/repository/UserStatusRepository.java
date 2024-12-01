package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

}
