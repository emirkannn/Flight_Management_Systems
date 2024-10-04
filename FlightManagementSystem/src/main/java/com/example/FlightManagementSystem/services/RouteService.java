package com.example.FlightManagementSystem.services;

import com.example.FlightManagementSystem.Dto.CreateRouteDto;
import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.Dto.UpdateRouteDto;

import java.util.List;

public interface RouteService {
    List<RouteDto> getAllRoutes();
    RouteDto getRouteById(Long id);
    void deleteOneRoute(Long id);
    RouteDto updateByRoute(Long id, UpdateRouteDto updateRouteDto);
    RouteDto createOneRoute(CreateRouteDto createRouteDto);
}
