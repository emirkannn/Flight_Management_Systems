package com.example.FlightManagementSystem.repos;

import com.example.FlightManagementSystem.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Long> {
}
