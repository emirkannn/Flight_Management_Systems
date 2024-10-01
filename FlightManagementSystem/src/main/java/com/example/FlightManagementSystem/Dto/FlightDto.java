package com.example.FlightManagementSystem.Dto;

import com.example.FlightManagementSystem.entities.Flight;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FlightDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String flightNumber;
    private Long route_id;
    private Long departure_airport_id;  // Kalkış havaalanı
    private Long arrival_airport_id;     // Varış havaalanı
    private int price;
    private LocalDateTime departureTime; // kalkış saati
    private LocalDateTime arrivalTime; // varış saati
    private int capacity;

    public FlightDto(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.route_id = flight.getRoute().getId();
        this.departure_airport_id = flight.getDepartureAirport().getId() ;
        this.arrival_airport_id = flight.getArrivalAirport().getId() ;
        this.price = flight.getPrice();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.capacity = flight.getCapacity();
    }
}
