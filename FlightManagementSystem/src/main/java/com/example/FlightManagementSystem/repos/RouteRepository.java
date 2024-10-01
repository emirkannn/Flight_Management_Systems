package com.example.FlightManagementSystem.repos;

import com.example.FlightManagementSystem.entities.Route;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends JpaRepository<Route,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Route r WHERE r.source.id = :airportId OR r.destination.id = :airportId1")
    void deleteBySourceIdOrDestinationId(@Param("airportId") Long airportId, @Param("airportId1") Long airportId1);

}
