# Introduction #

RoutePlanner was completed with a team of nine people including me while training at the FDM Academy. I was responsible for wiring the classes with annotations, setting up the Spring Security, setting up Spring AOP and general code review.


---

## Project Specifications ##

  * Allow user to find the shortest route(s) between two stations.
  * The "shortest route" is the route that has the least number of stops and the least number of line transfers.
  * The user must register an account and login before being able to use the web application.
  * The user has the option of buying a ticket after searching a route.
  * Allow the client to view the ticket purchase history.
  * Log any errors that may occur on the website such as simultaneous ticket buying on the same account.


---

## How to run the project ##
  1. Install Apache Tomcat onto your computer
  1. Get Eclipse IDE
  1. Download Apache Tomcat plugin for Eclipse
  1. Configure Tomcat on your Eclipse
  1. Right click project, Tomcat project -> Update context definition
  1. Run Tomcat
  1. Open your browser and type localhost:8080/RoutePlanner



---

## Configurations ##
### Change Route Map ###
The project is configured to load the subway map of the London Underground, however this can be changed! To change to a map of your choice, you must have an xml file in this format:
```
<network>
   <line name="some line">
      <station>Station A</station>
   </line>
</network>
```

Next put the xml file in the WEB-INF/src folder.
After that in filename.properties change the file name of CompleteTube.xml to your xml file.


---

### Change Zone Data ###
Zones determine the pricing of the tickets. A station with a lower zone has a higher ticket price and vice-versa. To determine the price in a route, the station with the lowest zone is used. To change to a zone data map of your choice, you must have a dat file in this format:
```
Station A:ZONE // Where ZONE >= 0 and <= 10
```

Next put the dat file in the WEB-INF/src folder.
After that in filename.properties change the file name of zone\_data2.dat to your dat file. NOTE that the stations in the dat file must correspond to the stations found in the xml file!!!