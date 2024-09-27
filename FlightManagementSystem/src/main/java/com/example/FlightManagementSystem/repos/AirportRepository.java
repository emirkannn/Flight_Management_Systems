package com.example.FlightManagementSystem.repos;

import com.example.FlightManagementSystem.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
