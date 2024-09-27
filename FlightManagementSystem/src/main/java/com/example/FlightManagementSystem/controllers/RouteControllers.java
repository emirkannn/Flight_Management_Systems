package com.example.FlightManagementSystem.controllers;

import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.entities.Route;
import com.example.FlightManagementSystem.response.RouteResponse;
import com.example.FlightManagementSystem.services.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/routes")
public class RouteControllers {

    private final RouteService routeService;
    public RouteControllers(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{routeId}")
    public RouteResponse getRouteResponse(@PathVariable Long routeId) {
        Route route = routeService.getRouteById(routeId);
        if (route == null) {
            return null;
        }
        return new RouteResponse(route);
    }
    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        routeService.deleteOneAirport(id);
    }
    @PutMapping("{id}")
    public Route updateRoute(@PathVariable Long id, @RequestBody RouteDto routeDTO) {
        return routeService.updateByRoute(id,routeDTO);
    }
    @PostMapping()
    public Route createRoute(@RequestBody RouteDto routeDto) {
        return routeService.createOneRoute(routeDto);

    }
}
