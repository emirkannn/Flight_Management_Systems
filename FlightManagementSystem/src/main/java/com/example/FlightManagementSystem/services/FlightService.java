package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.FlightDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Flight;
import com.example.FlightManagementSystem.entities.FlightStatusEnum;
import com.example.FlightManagementSystem.entities.Route;
import com.example.FlightManagementSystem.repos.AirportRepository;
import com.example.FlightManagementSystem.repos.FlightRepository;
import com.example.FlightManagementSystem.repos.RouteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final RouteRepository routeRepository;

    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository,RouteRepository routeRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.routeRepository =routeRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }
    public Route getFlightRoute(Long flightId) {
       Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight != null) {
            return flight.getRoute(); // İniş uçuşlarını döndürür
        }
        return null;
    }

    public Flight updateByFlight(Long id, FlightDto flightdto) {
        Optional<Flight> oldFlight = flightRepository.findById(id);
        if (oldFlight.isPresent()) {
            Flight flight = oldFlight.get();
            flight.setFlightNumber(flightdto.getFlightNumber());
            flight.setCapacity(flightdto.getCapacity());
            flight.setDepartureTime(flightdto.getDepartureTime());
            flight.setArrivalTime(flightdto.getArrivalTime());
            flight.setPrice(flightdto.getPrice());
            Route route = routeRepository.findById(flightdto.getRoute_id()).orElse(null);
            flight.setRoute(route);
            Airport departureAirport = airportRepository.findById(flightdto.getDeparture_airport_id()).orElse(null);
            flight.setDepartureAirport(departureAirport);
            Airport arrivelAirport = airportRepository.findById(flightdto.getArrival_airport_id()).orElse(null);
            flight.setArrivalAirport(arrivelAirport);
            flightRepository.save(flight);
            return flight;
        }else {
            return null;
        }
    }

    public Flight saveOneFlight(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDto.getFlightNumber());
        Airport departureAirport = airportRepository.findById(flightDto.getDeparture_airport_id()).orElse(null);
        flight.setDepartureAirport(departureAirport);
        Airport arrivelAirport = airportRepository.findById(flightDto.getArrival_airport_id()).orElse(null);
        flight.setArrivalAirport(arrivelAirport);
        Route route = routeRepository.findById(flightDto.getRoute_id()).orElse(null);
        flight.setRoute(route);
        flight.setPrice(flightDto.getPrice());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setCapacity(flightDto.getCapacity());
        flight.setStatus(FlightStatusEnum.SCHEDULED);
        return flightRepository.save(flight);
    }

    public void deleteOneFlight(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            flightRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Flight not found with id: " + id);
        }
    }
}
