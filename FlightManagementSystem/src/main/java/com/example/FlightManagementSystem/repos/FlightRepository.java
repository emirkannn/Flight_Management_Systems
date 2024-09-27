package com.example.FlightManagementSystem.repos;

import com.example.FlightManagementSystem.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Modifying
    @Transactional // Ensure this method runs within a transaction
    @Query("DELETE FROM Flight f WHERE f.route.id = :id")
    void deleteByRouteId(Long id);
}
