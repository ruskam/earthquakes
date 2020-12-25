package com.rustam.earthquakes.util;

import org.springframework.stereotype.Service;

@Service
public class Distance implements IDistance{

    public int calculate(double lat1, double lon1, double lat2,  double lon2) {
        final int R = 6379;
        double latDistance = toRad(lat2-lat1);
        double lonDistance = toRad(lon2-lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (int) Math.round(R * c);
    }


    private double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
