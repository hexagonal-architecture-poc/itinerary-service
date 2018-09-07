FROM java:8-jre-alpine

ADD target/itinerary-service-*.jar /itinerary-service.jar

ENTRYPOINT ["java", "-Dserver.port=8080", "-Dspring.profiles.active=docker", "-jar", "./itinerary-service.jar"] 
