package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.AirportDto;
import com.example.FlightManagementSystem.Dto.FlightDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Flight;
import com.example.FlightManagementSystem.repos.AirportRepository;
import com.example.FlightManagementSystem.repos.FlightRepository;
import com.example.FlightManagementSystem.repos.RouteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final  RouteRepository routeRepository;
    private final  FlightRepository flightRepository;


    public AirportService(AirportRepository airportRepository,
                          RouteRepository routeRepository,FlightRepository flightRepository ) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
        this.routeRepository = routeRepository;

    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
    public Airport getAirports(Long id) {
        return airportRepository.findById(id).orElse(null);
    }
    public List<FlightDto> getDepartureFlights(Long airportId) {
        Airport airport = airportRepository.findById(airportId).orElse(null);
        if (airport != null) {
            return airport.getDepartureFlights().stream()
                    .map(FlightDto::new)
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

    public Airport saveOneAirport(AirportDto newAirportDto) {

        Airport airport = new Airport();
        airport.setId(newAirportDto.getId());
        airport.setName(newAirportDto.getName());
        airport.setCode(newAirportDto.getCode());
        airport.setCountry(newAirportDto.getCountry());
        airport.setCity(newAirportDto.getCity());
        return airportRepository.save(airport);
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

    @Transactional
    public void deleteAirport(Long airportId) {
        routeRepository.deleteBySourceIdOrDestinationId(airportId, airportId); // İlgili rotaları sil
        flightRepository.deleteByDepartureAirportIdOrArrivalAirportId(airportId, airportId); // İlgili uçuşları sil
        airportRepository.deleteById(airportId); // Havaalanını sil
    }

}
