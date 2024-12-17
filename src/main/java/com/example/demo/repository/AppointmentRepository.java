package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByListingId(Long listingId);
	
	@Query(value = "SELECT a.*, l.* FROM appointment a LEFT JOIN listings l ON a.listing_id = l.id WHERE a.user_id = 1"
			, nativeQuery = true)
	List<Appointment> findAppointmentsWithListingsByUserId(@Param("userId") Long userId);
}
