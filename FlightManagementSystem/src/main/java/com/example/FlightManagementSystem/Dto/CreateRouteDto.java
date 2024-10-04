package com.example.FlightManagementSystem.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRouteDto {

    private long sourceAirportId;  // Source Airport ID
    private long destinationAirportId;  // Destination Airport ID
    private int distanceInMiles;

    public CreateRouteDto() {
    }
}
