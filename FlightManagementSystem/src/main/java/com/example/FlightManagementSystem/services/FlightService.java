package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.CreateFlightDto;
import com.example.FlightManagementSystem.Dto.FlightDto;
import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.Dto.UpdateFlightDto;

import java.util.List;

public interface FlightService {
    List<FlightDto> getAllFlights();
    FlightDto getFlightById(Long id);
    RouteDto getFlightRoute(Long flightId);
    FlightDto updateByFlight(Long id, UpdateFlightDto updateflightDto);
    FlightDto saveOneFlight(CreateFlightDto createlightDto);
    void deleteOneFlight(Long id);
}
