package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Route;
import com.example.FlightManagementSystem.repos.AirportRepository;
import com.example.FlightManagementSystem.repos.FlightRepository;
import com.example.FlightManagementSystem.repos.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public RouteService(RouteRepository routeRepository, FlightRepository flightRepository, AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public void deleteOneAirport(Long id) {
        flightRepository.deleteByRouteId(id);
        routeRepository.deleteById(id);
    }

    public Route updateByRoute(Long id, RouteDto routeDto) {
        Optional<Route> oldRoute = routeRepository.findById(id);
        if (oldRoute.isPresent()) {
            Route route = oldRoute.get();
            route.setDistanceInMiles(routeDto.getDistanceInMiles());
            routeRepository.save(route);
            return route;
        }else {
            return null;
        }

    }

    public Route createOneRoute(RouteDto routeDto) {
        Route route = new Route();
        route.setDistanceInMiles(routeDto.getDistanceInMiles());
        Airport destination = airportRepository.findById(routeDto.getDestinationId()).orElse(null);
        route.setDestination(destination);
        Airport source = airportRepository.findById(routeDto.getSourceId()).orElse(null);
        route.setSource(source);
        return routeRepository.save(route);
    }
}
