package com.example.FlightManagementSystem.controllers;

import com.example.FlightManagementSystem.Dto.FlightDto;
import com.example.FlightManagementSystem.entities.Flight;
import com.example.FlightManagementSystem.entities.Route;
import com.example.FlightManagementSystem.services.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/flights")
public class FlightControllers {

    private final FlightService flightService;
    public FlightControllers(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}")
    public FlightDto  getFlightRDto(@PathVariable Long flightId) {
        Flight flight = flightService.getFlightById(flightId);
        return new FlightDto(flight);
    }
    @GetMapping("/{flightId}/route")
    public Route getFlightRoute(@PathVariable Long flightId) {
        return flightService.getFlightRoute(flightId);
    }
    @PostMapping
    public Flight createFlight(@RequestBody FlightDto flightDto) {
        return flightService.saveOneFlight(flightDto);
    }
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteOneFlight(id);
    }
    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody FlightDto flightDto) {
        return flightService.updateByFlight(id,flightDto);

    }
}
