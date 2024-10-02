package com.example.FlightManagementSystem.controllers;

import com.example.FlightManagementSystem.Dto.RouteDto;
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
    public List<RouteDto> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{routeId}")
    public RouteDto getRouteResponse(@PathVariable Long routeId) {
        return routeService.getRouteById(routeId);
    }
    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        routeService.deleteOneAirport(id);
    }
    @PutMapping("{id}")
    public RouteDto updateRoute(@PathVariable Long id, @RequestBody RouteDto routeDTO) {
        return routeService.updateByRoute(id,routeDTO);
    }
    @PostMapping()
    public RouteDto createRoute(@RequestBody RouteDto routeDto) {
        return routeService.createOneRoute(routeDto);

    }
}
