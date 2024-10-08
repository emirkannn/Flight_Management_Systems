package com.example.FlightManagementSystem.Dto;


import com.example.FlightManagementSystem.entities.Airport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AirportDto {
    private Long id;
    private String name;
    private String code;
    private String country;
    private String city;

    public AirportDto(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.code = airport.getCode();
        this.country = airport.getCountry();
        this.city = airport.getCity();
    }
}
