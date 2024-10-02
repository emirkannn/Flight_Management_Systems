package com.example.FlightManagementSystem.services.impl;

import com.example.FlightManagementSystem.Dto.AirportDto;
import com.example.FlightManagementSystem.Dto.FlightDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.repos.AirportRepository;
import com.example.FlightManagementSystem.repos.FlightRepository;
import com.example.FlightManagementSystem.repos.RouteRepository;
import com.example.FlightManagementSystem.services.AirportService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final RouteRepository routeRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public AirportServiceImpl(AirportRepository airportRepository,
                              RouteRepository routeRepository,
                              FlightRepository flightRepository,
                              ModelMapper modelMapper) {
        this.airportRepository = airportRepository;
        this.routeRepository = routeRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AirportDto> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(airport -> modelMapper.map(airport, AirportDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AirportDto getAirports(Long id) {
        return airportRepository.findById(id)
                .map(airport -> modelMapper.map(airport, AirportDto.class))
                .orElse(null);
    }

    @Override
    public List<FlightDto> getDepartureFlights(Long airportId) {
        return airportRepository.findById(airportId)
                .map(airport -> airport.getDepartureFlights().stream()
                        .map(flight -> modelMapper.map(flight, FlightDto.class))
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public List<FlightDto> getArrivalFlights(Long airportId) {
        return airportRepository.findById(airportId)
                .map(airport -> airport.getArrivalFlights().stream()
                        .map(flight -> modelMapper.map(flight, FlightDto.class))
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public AirportDto saveOneAirport(AirportDto newAirportDto) {
        Airport airport = modelMapper.map(newAirportDto, Airport.class);
        Airport savedAirport = airportRepository.save(airport);
        return modelMapper.map(savedAirport, AirportDto.class);
    }

    @Override
    public AirportDto updateByAirport(Long id, AirportDto airportDto) {
        Optional<Airport> airportOptional = airportRepository.findById(id);
        if (airportOptional.isPresent()) {
            Airport airport = airportOptional.get();
            modelMapper.map(airportDto, airport);
            airportRepository.save(airport);
            return modelMapper.map(airport, AirportDto.class);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteAirport(Long airportId) {
        routeRepository.deleteBySourceIdOrDestinationId(airportId, airportId); // İlgili rotaları sil
        flightRepository.deleteByDepartureAirportIdOrArrivalAirportId(airportId, airportId); // İlgili uçuşları sil
        airportRepository.deleteById(airportId); // Havaalanını sil
    }
}
