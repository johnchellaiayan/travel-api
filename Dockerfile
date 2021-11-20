FROM openjdk:8-jdk-alpine
MAINTAINER Geasus
COPY target/Travelagency-ui-0.0.1.war Travelagency-ui-0.0.1.war
ENTRYPOINT ["java","-jar","Travelagency-ui-0.0.1.war"]

EXPOSE 7070