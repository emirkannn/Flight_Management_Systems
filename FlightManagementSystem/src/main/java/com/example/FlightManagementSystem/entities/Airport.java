package com.example.FlightManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "airport")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, length = 3, nullable = false)// benzersiz
    private String code;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "departureAirport",cascade = CascadeType.ALL, fetch = FetchType.LAZY) // kalkışlar
    @JsonBackReference("departure-airport") // döngüyü önler
    private List<Flight> departureFlights;

    @JsonBackReference("arrivel-airport")
    @OneToMany(mappedBy = "arrivalAirport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)// inişler
    private List<Flight> arrivalFlights;


}
