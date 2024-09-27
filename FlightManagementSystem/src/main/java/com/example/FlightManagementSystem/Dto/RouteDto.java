package com.example.FlightManagementSystem.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteDto {
    private Long id;
    @JsonProperty("source_id")
    private Long sourceId;

    @JsonProperty("destination_id")
    private Long destinationId;

    @JsonProperty("distance_in_miles")
    private int distanceInMiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public int getDistanceInMiles() {
        return distanceInMiles;
    }

    public void setDistanceInMiles(int distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }
}
