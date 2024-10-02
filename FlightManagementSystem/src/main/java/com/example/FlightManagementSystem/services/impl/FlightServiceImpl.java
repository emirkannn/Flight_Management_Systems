package com.example.FlightManagementSystem.services.impl;

import com.example.FlightManagementSystem.Dto.FlightDto;
import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Flight;
import com.example.FlightManagementSystem.entities.FlightStatusEnum;
import com.example.FlightManagementSystem.entities.Route;
import com.example.FlightManagementSystem.repos.AirportRepository;
import com.example.FlightManagementSystem.repos.FlightRepository;
import com.example.FlightManagementSystem.repos.RouteRepository;
import com.example.FlightManagementSystem.services.FlightService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public FlightServiceImpl(FlightRepository flightRepository, AirportRepository airportRepository,
                             RouteRepository routeRepository, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FlightDto> getAllFlights() {
        // Fetch all flights and map to FlightDto
        return flightRepository.findAll().stream()
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto getFlightById(Long id) {
        // Fetch flight by ID and map to FlightDto
        return flightRepository.findById(id)
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .orElse(null);
    }

    @Override
    public RouteDto getFlightRoute(Long flightId) {
        // Fetch flight by ID and get the associated route
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight != null && flight.getRoute() != null) {
            // Map the Route entity to RouteDto using ModelMapper
            return modelMapper.map(flight.getRoute(), RouteDto.class);
        }
        return null;
    }

    @Override
    public FlightDto updateByFlight(Long id, FlightDto flightDto) {
        Optional<Flight> oldFlight = flightRepository.findById(id);
        if (oldFlight.isPresent()) {
            Flight flight = oldFlight.get();
            modelMapper.map(flightDto, flight);
            Route route = routeRepository.findById(flightDto.getRoute().getId()).orElse(null);
            flight.setRoute(route);
            Airport departureAirport = airportRepository.findById(flightDto.getDepartureAirport().getId()).orElse(null);
            flight.setDepartureAirport(departureAirport);
            Airport arrivalAirport = airportRepository.findById(flightDto.getArrivalAirport().getId()).orElse(null);
            flight.setArrivalAirport(arrivalAirport);

            // Save and return the updated FlightDto
            Flight updatedFlight = flightRepository.save(flight);
            return modelMapper.map(updatedFlight, FlightDto.class);
        } else {
            return null;
        }
    }

    @Override
    public FlightDto saveOneFlight(FlightDto flightDto) {
        Flight flight = modelMapper.map(flightDto, Flight.class);

        Airport departureAirport = airportRepository.findById(flightDto.getDepartureAirport().getId()).orElse(null);
        flight.setDepartureAirport(departureAirport);
        Airport arrivalAirport = airportRepository.findById(flightDto.getArrivalAirport().getId()).orElse(null);
        flight.setArrivalAirport(arrivalAirport);
        Route route = routeRepository.findById(flightDto.getRoute().getId()).orElse(null);
        flight.setRoute(route);
        flight.setStatus(FlightStatusEnum.SCHEDULED);

        Flight savedFlight = flightRepository.save(flight);
        return modelMapper.map(savedFlight, FlightDto.class);
    }

    @Override
    public void deleteOneFlight(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            flightRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Flight not found with id: " + id);
        }
    }
}
