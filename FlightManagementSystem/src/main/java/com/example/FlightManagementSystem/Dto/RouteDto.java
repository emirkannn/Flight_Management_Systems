package com.example.FlightManagementSystem.Dto;

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

}
