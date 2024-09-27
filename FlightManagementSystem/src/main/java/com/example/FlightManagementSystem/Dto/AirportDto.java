package com.example.FlightManagementSystem.Dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AirportDto {
    private Long id;
    private String name;
    private String code;
    private String country;
    private String city;


}
