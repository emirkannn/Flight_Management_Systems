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

    @Column(nullable = false)
    private String flightNumber;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    @JsonBackReference // sonsuz döngüyü önler
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id", nullable = false)
    @JsonManagedReference("departure-airport")
    private Airport departureAirport;  // Kalkış havaalanı

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    @JsonManagedReference("arrival-airport")
    private Airport arrivalAirport;     // Varış havaalanı

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private LocalDateTime departureTime; // kalkış saati

    @Column(nullable = false)
    private LocalDateTime arrivalTime; // varış saati

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING) // enum veritabanında string olarak saklanacak
    @Column(nullable = false)
    private FlightStatusEnum status = FlightStatusEnum.SCHEDULED; // planlanmış

    public void updateStatus(LocalDateTime currentTime) {
        if (currentTime.isAfter(departureTime) && status == FlightStatusEnum.SCHEDULED) {
            status = FlightStatusEnum.DEPARTED; // Update status to Departed
        }
        if (currentTime.isAfter(arrivalTime) && status == FlightStatusEnum.DEPARTED) { // ayrılmış
            status = FlightStatusEnum.ARRIVED; // Update status to Arrived
        }
    }
}
