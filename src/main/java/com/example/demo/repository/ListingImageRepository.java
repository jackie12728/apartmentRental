package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entity.ListingImage;

public interface ListingImageRepository extends JpaRepository<ListingImage, Long> {

	// 根據 listing_id 查詢圖片，並以 id 升冪排序
    List<ListingImage> findByListingIdOrderByIdAsc(@Param("listing_id") Long listingId);
}
