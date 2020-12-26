package com.rustam.earthquakes.util;

import com.rustam.earthquakes.model.Earthquake;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterToConsole implements IPrinterToConsole{
    @Override
    public void print(List<Earthquake> earthquakes) {
        for (Earthquake e : earthquakes) {
            System.out.println(e.getTitle() + " || " + e.getDistance());
        }
    }
}
