package com.example.FlightManagementSystem.controllers;

import com.example.FlightManagementSystem.Dto.CreateRouteDto;
import com.example.FlightManagementSystem.Dto.RouteDto;
import com.example.FlightManagementSystem.Dto.UpdateRouteDto;
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
    public void deleteRoute(@PathVariable Long id) {
        routeService.deleteOneRoute(id);
    }
    @PutMapping("{id}")
    public RouteDto updateRoute(@PathVariable Long id, @RequestBody UpdateRouteDto updateRouteDto) {
        return routeService.updateByRoute(id,updateRouteDto);
    }
    @PostMapping()
    public RouteDto createRoute(@RequestBody CreateRouteDto createRouteDto) {
        return routeService.createOneRoute(createRouteDto);

    }
}
