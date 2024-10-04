package com.example.FlightManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "routes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "source_id", nullable = false)
    @JsonIgnoreProperties({"departureFlights", "arrivalFlights"})  // Döngüye girmemesi için ilişkili alanlar ignore edilir
    private Airport source; // kalkış havalimanı

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "destination_id", nullable = false)
    @JsonIgnoreProperties({"departureFlights", "arrivalFlights"})  // Döngüye girmemesi için ilişkili alanlar ignore edilir
    private Airport destination; // iniş havalimanı

    @Column(name = "distance_in_miles", nullable = false)
    private int distanceInMiles;
}
