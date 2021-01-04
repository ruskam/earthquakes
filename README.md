# Earthquakes

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
