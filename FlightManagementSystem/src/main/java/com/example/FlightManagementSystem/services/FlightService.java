package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.FlightDto;
import com.example.FlightManagementSystem.Dto.RouteDto;

import java.util.List;

public interface FlightService {
    List<FlightDto> getAllFlights();
    FlightDto getFlightById(Long id);
    RouteDto getFlightRoute(Long flightId);
    FlightDto updateByFlight(Long id, FlightDto flightDto);
    FlightDto saveOneFlight(FlightDto flightDto);
    void deleteOneFlight(Long id);
}
