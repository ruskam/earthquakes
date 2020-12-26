# Earthquakes-service

## How to run

There are two ways to run the app

### From Intellij IDEA 

Open the downloaded source code in Intellij IDEA, and run the EarthquakesApplication.java

### Run the jar

Download the earthquakes/target/earthquakes-service.jar and run in the terminal 
```bash
java -jar earthquakes-service.jar 
```

### Where to see the outcome

The application will be availale on the localhos at port 8081:
```bash
http://localhost:8081/api/earthquake/lat/{lat}/lon/{lon}
```
Passing latitude and lontide as a must.


#### NOTE: The instructions are test on MacOS