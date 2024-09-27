package com.example.FlightManagementSystem.controllers;

import com.example.FlightManagementSystem.Dto.AirportDto;
import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Flight;
import com.example.FlightManagementSystem.response.AirportResponse;
import com.example.FlightManagementSystem.response.FlightResponse;
import com.example.FlightManagementSystem.services.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/airports")
public class AirportControllers {

    private final AirportService airportService;

    public AirportControllers(AirportService airportService) {
        this.airportService = airportService;
    }
    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }
    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.saveOneAirport(airport);
    }

    @PutMapping(value = "/{id}")
    public Airport updateAirport(@PathVariable Long id, @RequestBody AirportDto airportDto) {
        return airportService.updateByAirport(id, airportDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteOneAirport(id);
    }

    @GetMapping("/{airportId}")
    public AirportResponse getAirportResponse(@PathVariable Long airportId) {
        Airport airport = airportService.getAirports(airportId);
        if (airport == null) {
            return null;
        }
        return new AirportResponse(airport);
    }
    // Belirli bir havaalanından kalkan uçuşları getirir
    @GetMapping("/{id}/departure-flights")
    public List<FlightResponse> getDepartureFlights(@PathVariable Long id) {
        return airportService.getDepartureFlights(id);
    }

    // Belirli bir havaalanına iniş yapan uçuşları getirir
    @GetMapping("/{id}/arrival-flights")
    public List<Flight> getArrivalFlights(@PathVariable Long id) {
        return airportService.getArrivalFlights(id);
    }
}
