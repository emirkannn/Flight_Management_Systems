package com.example.FlightManagementSystem.Dto;

import com.example.FlightManagementSystem.entities.FlightStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateFlightDto {
    private String flightNumber;
    private long routeId;  // Route ID
    private long departureAirportId;  // Kalkış Havaalanı ID
    private long arrivalAirportId;    // Varış Havaalanı ID
    private int price;
    private LocalDateTime departureTime;  // kalkış saati
    private LocalDateTime arrivalTime;    // varış saati
    private int capacity;
    private FlightStatusEnum status;      // Uçuş durumu
}
