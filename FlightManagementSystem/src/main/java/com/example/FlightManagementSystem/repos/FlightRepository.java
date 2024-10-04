package com.example.FlightManagementSystem.repos;

import com.example.FlightManagementSystem.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Modifying
    @Transactional // Ensure this method runs within a transaction
    @Query("DELETE FROM Flight f WHERE f.route.id = :id")
    void deleteByRouteId(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Flight f WHERE f.departureAirport.id = :airportId OR f.arrivalAirport.id = :airportId1")
    void deleteByDepartureAirportIdOrArrivalAirportId(@Param("airportId") Long airportId, @Param("airportId1") Long airportId1);

    @Modifying
    @Transactional
    @Query("UPDATE Flight f SET f.route = null WHERE f.route.id = :routeId")
    void clearRouteFromFlights(@Param("routeId") Long routeId);

    @Query("SELECT f FROM Flight f WHERE f.route.id = :id")
    List<Flight> findByRouteId(Long id);
}
