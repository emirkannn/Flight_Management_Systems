package com.example.FlightManagementSystem.response;

import com.example.FlightManagementSystem.entities.Route;
import lombok.Data;

@Data
public class RouteResponse {

    private Long id;
    private int distanceInMiles;

    public RouteResponse(Route route) {
        this.id = route.getId();
        this.distanceInMiles = route.getDistanceInMiles();
    }

}
