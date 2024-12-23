package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {
	
	@Modifying
	@Query(value = """
					DELETE FROM user_listing
					WHERE listing_id = :listingId
				   """, nativeQuery = true)
	void deleteUserListingByListingId(@Param("listingId") Long listingId);
	
	@Modifying
	@Query(value = """
					DELETE FROM appointment
					WHERE listing_id = :listingId
				   """, nativeQuery = true)
	void deleteAppointmentByListingId(@Param("listingId") Long listingId);
	
	@Modifying
	@Query(value = """
					DELETE FROM listing_image
					WHERE listing_id = :listingId
				   """, nativeQuery = true)
	void deleteListingImageByListingId(@Param("listingId") Long listingId);
	
	@Modifying
	@Query(value = """
					DELETE FROM review
					WHERE listing_id = :listingId
				   """, nativeQuery = true)
	void deleteReviewByListingId(@Param("listingId") Long listingId);
	
	@Modifying
	@Query(value = """
					DELETE FROM listings
					WHERE id = :listingId
				   """, nativeQuery = true)
	void deleteListingByListingId(@Param("listingId") Long listingId);
}
