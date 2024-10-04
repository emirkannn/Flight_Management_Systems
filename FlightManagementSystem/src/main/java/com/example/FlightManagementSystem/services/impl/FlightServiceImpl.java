package com.example.FlightManagementSystem.services.impl;

import com.example.FlightManagementSystem.Dto.CreateFlightDto;
import com.example.FlightManagementSystem.Dto.FlightDto;
import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.Dto.UpdateFlightDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Flight;
import com.example.FlightManagementSystem.entities.FlightStatusEnum;
import com.example.FlightManagementSystem.entities.Route;
import com.example.FlightManagementSystem.repos.AirportRepository;
import com.example.FlightManagementSystem.repos.FlightRepository;
import com.example.FlightManagementSystem.repos.RouteRepository;
import com.example.FlightManagementSystem.services.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
    public FlightDto updateByFlight(Long id, UpdateFlightDto updateFlightDto) {
        Optional<Flight> oldFlight = flightRepository.findById(id);
        if (oldFlight.isPresent()) {
            Flight flight = oldFlight.get();

            // Update the simple fields using the values from UpdateFlightDto
            flight.setFlightNumber(updateFlightDto.getFlightNumber());
            flight.setPrice(updateFlightDto.getPrice());
            flight.setDepartureTime(updateFlightDto.getDepartureTime());
            flight.setArrivalTime(updateFlightDto.getArrivalTime());
            flight.setCapacity(updateFlightDto.getCapacity());
            flight.setStatus(updateFlightDto.getStatus());

            // Fetch and set the Route by ID
            Route route = routeRepository.findById(updateFlightDto.getRouteId()).orElse(null);
            flight.setRoute(route);

            // Fetch and set the departure Airport by ID
            Airport departureAirport = airportRepository.findById(updateFlightDto.getDepartureAirportId()).orElse(null);
            flight.setDepartureAirport(departureAirport);

            // Fetch and set the arrival Airport by ID
            Airport arrivalAirport = airportRepository.findById(updateFlightDto.getArrivalAirportId()).orElse(null);
            flight.setArrivalAirport(arrivalAirport);

            // Save and return the updated FlightDto
            Flight updatedFlight = flightRepository.save(flight);
            return modelMapper.map(updatedFlight, FlightDto.class);
        } else {
            return null;
        }
    }


    public FlightDto saveOneFlight(CreateFlightDto createFlightDto) {
        Flight flight = new Flight();

        // Set fields directly from CreateFlightDto
        flight.setFlightNumber(createFlightDto.getFlightNumber());
        flight.setPrice(createFlightDto.getPrice());
        flight.setDepartureTime(createFlightDto.getDepartureTime());
        flight.setArrivalTime(createFlightDto.getArrivalTime());
        flight.setCapacity(createFlightDto.getCapacity());
        flight.setStatus(createFlightDto.getStatus());

        // Fetch and set the Route by ID
        Route route = routeRepository.findById(createFlightDto.getRouteId()).orElse(null);
        flight.setRoute(route);

        // Fetch and set the Departure Airport by ID
        Airport departureAirport = airportRepository.findById(createFlightDto.getDepartureAirportId()).orElse(null);
        flight.setDepartureAirport(departureAirport);

        // Fetch and set the Arrival Airport by ID
        Airport arrivalAirport = airportRepository.findById(createFlightDto.getArrivalAirportId()).orElse(null);
        flight.setArrivalAirport(arrivalAirport);

        // Set the initial status to "SCHEDULED"
        flight.setStatus(FlightStatusEnum.SCHEDULED);

        // Save and return the FlightDto
        Flight savedFlight = flightRepository.save(flight);
        return modelMapper.map(savedFlight, FlightDto.class);
    }


    @Override
    public void deleteOneFlight(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if (optionalFlight.isPresent()) {
            flightRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Flight not found with id " + id);
        }
    }
}
