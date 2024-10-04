package com.example.FlightManagementSystem.services.impl;

import com.example.FlightManagementSystem.Dto.CreateRouteDto;
import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.Dto.UpdateRouteDto;
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
    public void deleteOneRoute(Long id) {
        flightRepository.clearRouteFromFlights(id);
        routeRepository.deleteById(id);
    }

    @Override
    public RouteDto updateByRoute(Long id, UpdateRouteDto updateRouteDto) {
        Optional<Route> oldRoute = routeRepository.findById(id);
        if (oldRoute.isPresent()) {
            Route route = oldRoute.get();
            route.setDistanceInMiles(updateRouteDto.getDistanceInMiles()); // Set distance from UpdateRouteDto

            // Find and set the source and destination airports using the IDs from UpdateRouteDto
            Airport destination = airportRepository.findById(updateRouteDto.getDestinationAirportId()).orElse(null);
            route.setDestination(destination);

            Airport source = airportRepository.findById(updateRouteDto.getSourceAirportId()).orElse(null);
            route.setSource(source);

            // Save the updated route
            routeRepository.save(route);

            // Return the updated Route as a RouteDto using modelMapper
            return modelMapper.map(route, RouteDto.class);
        } else {
            return null; // Return null if the route doesn't exist
        }
    }

    @Override
    public RouteDto createOneRoute(CreateRouteDto createRouteDto) {
        Route route = new Route();
        Airport destination = airportRepository.findById(createRouteDto.getDestinationAirportId()).orElse(null);
        route.setDestination(destination);
        Airport source = airportRepository.findById(createRouteDto.getSourceAirportId()).orElse(null);
        route.setSource(source);
        route.setDistanceInMiles(createRouteDto.getDistanceInMiles());

        return modelMapper.map(routeRepository.save(route), RouteDto.class);
    }
}
