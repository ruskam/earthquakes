package com.rustam.earthquakes.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UsgsProperty {
    private Double mag;
    private String title;
    private Long time;
}
