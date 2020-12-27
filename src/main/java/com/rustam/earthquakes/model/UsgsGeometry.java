package com.rustam.earthquakes.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsgsGeometry {

    private Double[] coordinates;

    public UsgsGeometry() {
        coordinates = new Double[2];
    }

    public double getLat() {
        return this.coordinates[1];
    }

    public double getLon() {
        return this.coordinates[0];
    }

}
