package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.RouteDto;

import java.util.List;

public interface RouteService {
    List<RouteDto> getAllRoutes();
    RouteDto getRouteById(Long id);
    void deleteOneAirport(Long id);
    RouteDto updateByRoute(Long id, RouteDto routeDto);
    RouteDto createOneRoute(RouteDto routeDto);
}
