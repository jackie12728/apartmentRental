package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

}
