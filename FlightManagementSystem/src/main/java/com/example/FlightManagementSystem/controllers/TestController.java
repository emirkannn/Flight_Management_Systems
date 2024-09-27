package com.example.FlightManagementSystem.controllers;

import com.example.FlightManagementSystem.entities.Airport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Airport> testUpdate(@PathVariable Long id, @RequestBody Airport airport) {
        return ResponseEntity.ok(airport); // Simply return the received airport object
    }
}
