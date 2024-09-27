package com.example.FlightManagementSystem.Dto;

import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Route;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }

    public Long getDeparture_airport_id() {
        return departure_airport_id;
    }

    public void setDeparture_airport_id(Long departure_airport_id) {
        this.departure_airport_id = departure_airport_id;
    }

    public Long getArrival_airport_id() {
        return arrival_airport_id;
    }

    public void setArrival_airport_id(Long arrival_airport_id) {
        this.arrival_airport_id = arrival_airport_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
