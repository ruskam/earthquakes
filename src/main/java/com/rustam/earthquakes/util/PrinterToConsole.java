package com.rustam.earthquakes.util;

import com.rustam.earthquakes.model.Earthquake;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterToConsole implements IPrinterToConsole{
    @Override
    public void print(List<Earthquake> earthquakes) {
        /**I am aware of the smelly code due to printing to console
         *
         * I could have used slf4j.Logger but the output is not copy/paste friendly for testing*/
        for (Earthquake e : earthquakes) {
            System.out.println(e.getTitle() + " || " + e.getDistance());
        }
    }
}
