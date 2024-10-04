package com.example.FlightManagementSystem.controllers;

import com.example.FlightManagementSystem.Dto.AirportDto;
import com.example.FlightManagementSystem.Dto.FlightDto;
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
    public List<AirportDto> getAllAirports() {
        return airportService.getAllAirports();
    }
    @PostMapping
    public AirportDto createAirport(@RequestBody AirportDto airportDto) {
        return airportService.saveOneAirport(airportDto);
    }

    @PutMapping(value = "/{id}")
    public AirportDto updateAirport(@PathVariable Long id, @RequestBody AirportDto airportDto) {
        return airportService.updateByAirport(id, airportDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    @GetMapping("/{airportId}")
    public AirportDto getAirportDto(@PathVariable Long airportId) {
        return airportService.getAirports(airportId);
    }
    // Belirli bir havaalanından kalkan uçuşları getirir
    @GetMapping("/{id}/departure-flights")
    public List<FlightDto> getDepartureFlights(@PathVariable Long id) {
        return airportService.getDepartureFlights(id);
    }

    // Belirli bir havaalanına iniş yapan uçuşları getirir
    @GetMapping("/{id}/arrival-flights")
    public List<FlightDto> getArrivalFlights(@PathVariable Long id) {
        return airportService.getArrivalFlights(id);
    }
}
