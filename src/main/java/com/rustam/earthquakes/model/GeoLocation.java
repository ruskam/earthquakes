package com.rustam.earthquakes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class GeoLocation {
    private Double lat;
    private Double lon;

    public GeoLocation(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoLocation)) return false;
        GeoLocation that = (GeoLocation) o;
        return Objects.equals(getLat(), that.getLat()) && Objects.equals(getLon(), that.getLon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLon());
    }
}
