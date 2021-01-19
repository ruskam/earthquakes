package com.rustam.earthquakes.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class GeoLocation {
    private Double lat;
    private Double lon;
}
