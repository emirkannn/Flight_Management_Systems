package com.example.FlightManagementSystem.response;

import com.example.FlightManagementSystem.entities.Flight;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightResponse {

    private long id;
    private String flightNumber;
    private int price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int capacity;

    public FlightResponse(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.price = flight.getPrice();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.capacity = flight.getCapacity();
    }
}
