package com.example.FlightManagementSystem.response;

import com.example.FlightManagementSystem.entities.Airport;
import lombok.Data;

@Data
public class AirportResponse {

    private Long id;
    private String name;
    private String code;
    private String country;
    private String city;

    public AirportResponse(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.code = airport.getCode();
        this.country = airport.getCountry();
        this.city = airport.getCity();
    }
}
