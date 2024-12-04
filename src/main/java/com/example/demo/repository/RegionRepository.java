package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

	List<Region> findByCityId(Long cityId);
}
