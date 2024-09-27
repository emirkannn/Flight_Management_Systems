package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.AirportDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Flight;
import com.example.FlightManagementSystem.entities.Route;
import com.example.FlightManagementSystem.repos.AirportRepository;
import com.example.FlightManagementSystem.response.FlightResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
    public Airport getAirports(Long id) {
        return airportRepository.findById(id).orElse(null);
    }
    public List<FlightResponse> getDepartureFlights(Long airportId) {
        Airport airport = airportRepository.findById(airportId).orElse(null);
        if (airport != null) {
            return airport.getDepartureFlights().stream()
                    .map(FlightResponse::new)
                    .collect(Collectors.toList()); // Kalkış uçuşlarını döndürür
        }
        return null;
    }
    public List<Flight> getArrivalFlights(Long airportId) {
        Airport airport = airportRepository.findById(airportId).orElse(null);
        if (airport != null) {
            return airport.getArrivalFlights(); // İniş uçuşlarını döndürür
        }
        return null;
    }

    public Airport saveOneAirport(Airport newAirport) {
        return airportRepository.save(newAirport);
    }

    public Airport updateByAirport(Long id, AirportDto airportDto) {
        Optional<Airport> airportOptional = airportRepository.findById(id);
        if (airportOptional.isPresent()) {
            Airport airport = airportOptional.get();
            airport.setCode(airportDto.getCode());
            airport.setName(airportDto.getName());
            airport.setCountry(airportDto.getCountry());
            airport.setCity(airportDto.getCity());
            airportRepository.save(airport);
            return airport;
        }else {
            return null;
        }
    }

    public void deleteOneAirport(Long id) {
        airportRepository.deleteById(id);
    }
}
