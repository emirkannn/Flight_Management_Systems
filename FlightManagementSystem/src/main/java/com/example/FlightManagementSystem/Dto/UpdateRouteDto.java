package com.example.FlightManagementSystem.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRouteDto {

    private Long id;  // Route ID for identifying the route to be updated
    private long sourceAirportId;  // Source Airport ID
    private long destinationAirportId;  // Destination Airport ID
    private int distanceInMiles;

    public UpdateRouteDto() {
    }
}
