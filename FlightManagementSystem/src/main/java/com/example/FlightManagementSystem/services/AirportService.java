package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.AirportDto;
import com.example.FlightManagementSystem.Dto.FlightDto;

import java.util.List;

public interface AirportService {

    List<AirportDto> getAllAirports();

    AirportDto getAirports(Long id);

    List<FlightDto> getDepartureFlights(Long airportId);

    List<FlightDto> getArrivalFlights(Long airportId);

    AirportDto saveOneAirport(AirportDto newAirportDto);

    AirportDto updateByAirport(Long id, AirportDto airportDto);

    void deleteAirport(Long airportId);
}
