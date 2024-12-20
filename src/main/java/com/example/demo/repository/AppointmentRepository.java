package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByListingId(Long listingId);
	
	@Query(value = """
					SELECT appointment_date, appointment_time, address, listingname, schedule_name
					FROM appointment a LEFT JOIN listings l ON a.listing_id = l.id
					LEFT JOIN schedule s ON a.schedule_id = s.id
					WHERE a.user_id = :userId
					ORDER BY appointment_date, appointment_time
				   """, nativeQuery = true)
	List<Object[]> findAppointmentsWithListingsByUserId(@Param("userId") Long userId);
}
