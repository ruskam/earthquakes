# Problem
Read earthquake events from https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson
and find 10 earthquakes that happened in the closest proximity to the input location.

In detail, create a (REST) microservice that:
* Accepts two float numbers as request parameters that represent the lat/lon of any location
* Read a list of earthquakes that happened during last 30 days
* Calculate distance between given location and each of the earthquakes
* Provide a JSON response and also print the 10 earthquakes with the shortest distance to the given location
* The output list should contain earthquake magnitude, title, date, distance in kilometers and geographic coordinates
* If there is more than one earthquake event recorded in any location, there should be only one considered 

# Solution

## Technology stack
Spring Boot, Java 8+, WebFlux

## How to run

There are two ways to run the app

### From Intellij IDEA 

Open the downloaded source code in Intellij IDEA, and run the EarthquakesApplication.java

### Run the jar

Download the earthquakes/target/earthquakes-service.jar and run in the terminal 
```bash
java -jar earthquakes-service.jar 
```

### Where to see the output

There are currently two solutions provided: synchronous with blocking and asynchronous.

After the application is started, it is mandatory to open a browser and pass the latitude and longitude as part of the URL endpoint

Synchronous solution URI:

```bash
http://localhost:8081/api/earthquake/lat/{lat}/lon/{lon}
```

Asynchronous solution URI:

```bash
http://localhost:8081/api/earthquake/async/lat/{lat}/lon/{lon}
```

The output is available as a JSON response and in the console (if run from the Intellij IDEA) and in the terminal (if run through the terminal command)


#### NOTE: The instructions are tested on MacOS
