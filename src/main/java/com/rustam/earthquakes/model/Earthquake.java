package com.rustam.earthquakes.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Earthquake {
    private String id;
    private Double magnitude;
    private String title;
    private LocalDate date;
    private Integer distance;
    private GeoLocation coordinates;
}
