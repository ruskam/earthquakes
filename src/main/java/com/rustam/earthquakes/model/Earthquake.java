package com.rustam.earthquakes.model;

public class Earthquake {
    private String id;
    private Double magnitude;
    private String title;
    private Integer distance;

    public Earthquake() {
    }

    public Earthquake(String id, Double magnitude, String title, Integer distance) {
        this.id = id;
        this.magnitude = magnitude;
        this.title = title;
        this.distance = distance;
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

    @Override
    public String toString() {
        return "Earthquake{" +
                "id='" + id + '\'' +
                ", magnitude=" + magnitude +
                ", title='" + title + '\'' +
                ", distance=" + distance +
                '}';
    }
}
