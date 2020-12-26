package com.rustam.earthquakes.model;

import java.util.Objects;

public class Earthquake {
    private String id;
    private Double magnitude;
    private String title;
    private Integer distance;
    private GeoLocation coordinates;

    public Earthquake(Earthquake us7000cmj2) {
    }

    public Earthquake(String id, Double magnitude, String title, Integer distance, GeoLocation coordinates) {
        this.id = id;
        this.magnitude = magnitude;
        this.title = title;
        this.distance = distance;
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public GeoLocation getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoLocation coordinates) {
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

    @Override
    public String toString() {
        return "Earthquake{" +
                "id='" + id + '\'' +
                ", magnitude=" + magnitude +
                ", title='" + title + '\'' +
                ", distance=" + distance +
                ", coordinates=" + coordinates +
                '}';
    }
}
