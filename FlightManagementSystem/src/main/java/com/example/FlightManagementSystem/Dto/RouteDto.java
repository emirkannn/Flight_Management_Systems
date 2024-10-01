package com.example.FlightManagementSystem.Dto;

import com.example.FlightManagementSystem.entities.Route;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RouteDto {
    private Long id;
    @JsonProperty("source_id")
    private Long sourceId;

    @JsonProperty("destination_id")
    private Long destinationId;

    @JsonProperty("distance_in_miles")
    private int distanceInMiles;

    public RouteDto(Route route) {
        this.id = route.getId();
        this.sourceId = route.getSource().getId();
        this.destinationId = route.getDestination().getId();
        this.distanceInMiles = route.getDistanceInMiles();
    }

    public RouteDto() {
    }
}
