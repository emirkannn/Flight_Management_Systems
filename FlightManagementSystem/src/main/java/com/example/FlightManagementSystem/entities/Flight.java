package com.example.FlightManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "flights")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String flightNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "route_id")
    @JsonBackReference // sonsuz döngüyü önler
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "departure_airport_id")
    @JsonManagedReference("departure-airport")
    private Airport departureAirport;  // Kalkış havaalanı

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "arrival_airport_id", nullable = true)
    @JsonManagedReference("arrival-airport")
    private Airport arrivalAirport;     // Varış havaalanı

    private int price;
    private LocalDateTime departureTime; // kalkış saati

    private LocalDateTime arrivalTime; // varış saati

    private int capacity;

    @Enumerated(EnumType.STRING) // enum veritabanında string olarak saklanacak
    @Column(nullable = false)
    private FlightStatusEnum status = FlightStatusEnum.SCHEDULED; // planlanmış

}
