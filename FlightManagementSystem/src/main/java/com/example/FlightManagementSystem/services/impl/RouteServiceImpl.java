package com.example.FlightManagementSystem.services.impl;

import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Route;
import com.example.FlightManagementSystem.repos.AirportRepository;
import com.example.FlightManagementSystem.repos.FlightRepository;
import com.example.FlightManagementSystem.repos.RouteRepository;
import com.example.FlightManagementSystem.services.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository, FlightRepository flightRepository, AirportRepository airportRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteDto> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(route -> modelMapper.map(route, RouteDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RouteDto getRouteById(Long id) {
        return routeRepository.findById(id)
                .map(route -> modelMapper.map(route, RouteDto.class))
                .orElse(null);
    }

    @Override
    public void deleteOneAirport(Long id) {
        flightRepository.deleteByRouteId(id);
        routeRepository.deleteById(id);
    }

    @Override
    public RouteDto updateByRoute(Long id, RouteDto routeDto) {
        Optional<Route> oldRoute = routeRepository.findById(id);
        if (oldRoute.isPresent()) {
            Route route = oldRoute.get();
            route.setDistanceInMiles(routeDto.getDistanceInMiles());
            Airport destination = airportRepository.findById(routeDto.getDestination().getId()).orElse(null);
            route.setDestination(destination);
            Airport source = airportRepository.findById(routeDto.getSource().getId()).orElse(null);
            route.setSource(source);
            routeRepository.save(route);
            return modelMapper.map(route, RouteDto.class);
        } else {
            return null;
        }
    }

    @Override
    public RouteDto createOneRoute(RouteDto routeDto) {
        Route route = modelMapper.map(routeDto, Route.class);
        Airport destination = airportRepository.findById(routeDto.getDestination().getId()).orElse(null);
        route.setDestination(destination);
        Airport source = airportRepository.findById(routeDto.getSource().getId()).orElse(null);
        route.setSource(source);
        return modelMapper.map(routeRepository.save(route), RouteDto.class);
    }
}
