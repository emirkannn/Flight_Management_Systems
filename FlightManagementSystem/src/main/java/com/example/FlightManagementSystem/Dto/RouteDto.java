package com.example.FlightManagementSystem.Dto;

import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Route;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RouteDto {

    private Long id;
    private Airport source;
    private Airport destination;
    private int distanceInMiles;

    public RouteDto(Route route) {
        this.id = route.getId();
        this.source = route.getSource();
        this.destination = route.getDestination();
        this.distanceInMiles = route.getDistanceInMiles();
    }
}
