package com.rustam.earthquakes.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UsgsObservation {

    private UsgsProperty properties;
    private UsgsGeometry geometry;
    private String id;
}
