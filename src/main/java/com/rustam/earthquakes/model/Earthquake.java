package com.rustam.earthquakes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Earthquake {
    private String id;
    private Double magnitude;
    private String title;
    private Integer distance;
    private GeoLocation coordinates;

    public Earthquake(String id, Double magnitude, String title, Integer distance, GeoLocation coordinates) {
        this.id = id;
        this.magnitude = magnitude;
        this.title = title;
        this.distance = distance;
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Earthquake)) return false;
        Earthquake that = (Earthquake) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getMagnitude(), that.getMagnitude()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDistance(), that.getDistance()) && Objects.equals(getCoordinates(), that.getCoordinates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMagnitude(), getTitle(), getDistance(), getCoordinates());
    }

}
