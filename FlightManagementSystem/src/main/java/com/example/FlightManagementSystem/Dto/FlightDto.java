package com.example.FlightManagementSystem.Dto;

import com.example.FlightManagementSystem.entities.Flight;
import com.example.FlightManagementSystem.entities.FlightStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FlightDto {
    private long id;
    private String flightNumber;
    private RouteDto route;  // Route DTO
    private AirportDto departureAirport;  // Kalkış Havaalanı DTO
    private AirportDto arrivalAirport;    // Varış Havaalanı DTO
    private int price;
    private LocalDateTime departureTime;  // kalkış saati
    private LocalDateTime arrivalTime;    // varış saati
    private int capacity;
    private FlightStatusEnum status;      // Uçuş durumu

    public FlightDto(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.route = new RouteDto(flight.getRoute());  // Route DTO dönüşümü
        this.departureAirport = new AirportDto(flight.getDepartureAirport()); // Kalkış havaalanı DTO dönüşümü
        this.arrivalAirport = new AirportDto(flight.getArrivalAirport());     // Varış havaalanı DTO dönüşümü
        this.price = flight.getPrice();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.capacity = flight.getCapacity();
        this.status = flight.getStatus();
    }
}
