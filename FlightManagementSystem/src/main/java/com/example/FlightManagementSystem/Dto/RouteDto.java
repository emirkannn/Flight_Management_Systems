package com.example.FlightManagementSystem.Dto;

import com.example.FlightManagementSystem.entities.Airport;
import com.example.FlightManagementSystem.entities.Route;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

    public RouteDto() {
    }
}
